package com.intuit.cg.backendtechassessment.repository;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.intuit.cg.backendtechassessment.dto.ProjectDto;
import com.intuit.cg.backendtechassessment.dto.mapper.BidderMapper;
import com.intuit.cg.backendtechassessment.configuration.Bid;
@Repository
public class ProjRepoCustomImpl implements ProjRepoCustom {
	@PersistenceContext
    private EntityManager em;
	private static final Logger logger = LoggerFactory.getLogger(ProjRepoCustomImpl.class);
	public void RunSafe(String q) {
		try {
			Query query = this.em.createQuery(q);			
			logger.info("query {} res {}",q,query.getResultList().toString());			
		}catch(Exception e){logger.info("query {} ex {}",q,e.toString());}
	}
/*	below reads will go to 2 level index, as we defined in entity table,
	which will make the search faster .
	avoided nested query for readablity and efficiency
	this will take care of below 3 cases
	1) bidding not ended
	2) bidding ended , normal bid is minimum
	3) bidding ended , autobid is minimum
*/	
	@Override
	public void GetMinBid(ProjectDto projectDto,Date endDate) throws DataAccessException {
		Query query = this.em.createQuery("SELECT MIN(amount) from Bid where project_id=:ProjId");
    	query.setParameter("ProjId", projectDto.getProjectId());
    	logger.info("query.getResultList() {} endDate {} currdate {}",query.getResultList().get(0),endDate,new Date());
    	Double  b = (Double)query.getResultList().get(0);
    	if(endDate.after(new Date())) { //bidding not ended
//    	if(endDate.compareTo(new Date()) > 0) { //bidding not ended
			projectDto.setBiddingCompleted(false);
			if(b!=null)
				projectDto.setLowestBid(b);
		}else {
			ProcessWhenBiddingCompleted(projectDto,b);
		}
    	logger.info("proj {} endDate {}",projectDto);
	}
	public enum UseColumn {
	    AUTOBID,
	    NORMALBID,
	    NONE,
	}

	void ProcessWhenBiddingCompleted(ProjectDto projectDto,Double minBid) {
    	Bid bid = null;
    	Query query = this.em.createQuery("SELECT MIN(autoBid) from Bid where project_id=:ProjId");
    	query.setParameter("ProjId", projectDto.getProjectId());
    	//null is expected as autobid is not a mandatory field
    	Double minAutoBid = (Double) query.getResultList().get(0);
    	logger.info("MinautoBid ===>"+minAutoBid);
    	UseColumn useCol = UseColumn.NONE;
    	if(minAutoBid != null) {
        	if(minBid!=null) {
            	if(minAutoBid < minBid) {
            		useCol = UseColumn.AUTOBID;
            	}else {
            		useCol = UseColumn.NORMALBID;
            	}  		
        	}else {
        		useCol = UseColumn.AUTOBID;
        	}	
    	}else if(minBid != null) {
    		useCol = UseColumn.NORMALBID;
    	}
    	if(useCol != UseColumn.NONE) {
    		if(useCol == UseColumn.AUTOBID) {
    			query = this.em.createQuery("from Bid where project_id=:ProjId and autoBid=:value");	
    		}else {
    			query = this.em.createQuery("from Bid where project_id=:ProjId and amount=:value");    			
    		}    		
    		query.setParameter("ProjId", projectDto.getProjectId()).
    		setParameter("value", useCol == UseColumn.AUTOBID ? minAutoBid:minBid);    		
        	bid = (Bid)query.getSingleResult();
    	}
    	SetProjWhenBiddingCompleted(projectDto,bid,useCol);		
	}
	void SetProjWhenBiddingCompleted(ProjectDto projectDto,Bid bid,UseColumn useCol) {
		if(bid!=null) {
			//projectDto.setLowestBid(autoBid ? bid.getAutoBid() : bid.getAmount());
			projectDto.setLowestBid(useCol == UseColumn.AUTOBID ? bid.getAutoBid() : bid.getAmount());
			projectDto.setBidWinner(BidderMapper.INSTANCE.bidderToBidderDto(bid.getBidder()));	
		}
		projectDto.setBiddingCompleted(true);
	}
}
