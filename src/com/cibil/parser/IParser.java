package com.cibil.parser;

import java.io.IOException;
import java.util.List;

public  interface IParser {
	
	public Object convertExcelToList(Object p_data, Object p_fileproperties) throws  Exception;

}
