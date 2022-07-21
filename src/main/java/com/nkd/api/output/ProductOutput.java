package com.nkd.api.output;

import java.util.ArrayList;
import java.util.List;

import com.nkd.dto.ProductDTO;

public class ProductOutput {

	private List<ProductDTO> listResult = new ArrayList<>();

	public List<ProductDTO> getListResult() {
		return listResult;
	}

	public void setListResult(List<ProductDTO> listResult) {
		this.listResult = listResult;
	}

}
