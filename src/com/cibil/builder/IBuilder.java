package com.cibil.builder;

import java.util.ArrayList;

import com.cibil.bean.DataStore;
 
public interface IBuilder {


	public void build(ArrayList<DataStore> rejectedRecords);
}
