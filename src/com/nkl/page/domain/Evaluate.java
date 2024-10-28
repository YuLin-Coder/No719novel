package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Evaluate extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 939439379957423785L;
	private int evaluate_id; // 
	private int tbook_id; // 
	private int user_id; // 
	private int evaluate_score; // 
	private String evaluate_date; // 

	private String ids;
	private String random;

	public void setEvaluate_id(int evaluate_id){
		this.evaluate_id=evaluate_id;
	}

	public int getEvaluate_id(){
		return evaluate_id;
	}

	public void setTbook_id(int tbook_id){
		this.tbook_id=tbook_id;
	}

	public int getTbook_id(){
		return tbook_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setEvaluate_score(int evaluate_score){
		this.evaluate_score=evaluate_score;
	}

	public int getEvaluate_score(){
		return evaluate_score;
	}

	public void setEvaluate_date(String evaluate_date){
		this.evaluate_date=evaluate_date;
	}

	public String getEvaluate_date(){
		return evaluate_date;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

}
