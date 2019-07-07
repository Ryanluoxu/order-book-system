package io.ryanluoxu.orderbook.financialinstrument;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrument;
import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrumentInput;
import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrumentVO;
import io.ryanluoxu.orderbook.common.GenericRestController;
import io.ryanluoxu.orderbook.common.bean.ResponseModel;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.constant.StatusConstant;

@RestController
@RequestMapping("/rest/financialInstrument")
public class FinancialInstrumentRestController extends GenericRestController<FinancialInstrument, FinancialInstrumentVO, FinancialInstrumentInput> {

	@Autowired
	private FinancialInstrumentController financialInstrumentController;

	/**
	 * return one single VO
	 */
    @PostMapping("/add")
    public ResponseModel<FinancialInstrumentVO> add(@RequestBody FinancialInstrumentInput financialInstrumentInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_ADD, financialInstrumentInput, financialInstrumentController);
	}
	
	@PostMapping("/delete")
	public ResponseModel<FinancialInstrumentVO> delete(@RequestBody FinancialInstrumentInput financialInstrumentInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_DELETE, financialInstrumentInput, financialInstrumentController);
	}
	
	@PostMapping("/update")
	public ResponseModel<FinancialInstrumentVO> update(@RequestBody FinancialInstrumentInput financialInstrumentInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_UPDATE, financialInstrumentInput, financialInstrumentController);
	}

	/**
	 * return a list of VO
	 */
	@PostMapping("/findAll")
	public ResponseModel<List<FinancialInstrumentVO>> findAll(){
		ResponseModel<List<FinancialInstrumentVO>> response = new ResponseModel<>();
		try {
			List<FinancialInstrumentVO> financialInstrumentVOs = financialInstrumentController.findAll();
			response.setStatus(StatusConstant.RESPONSE_SUCCESS);
			response.setData(financialInstrumentVOs);
		} catch (Exception e) {
			response.setStatus(StatusConstant.RESPONSE_FAIL);
			response.setErrorMsg(e.toString());
		}
		return response;
	}

}
