package vip.dcpay.filesystem.domain.assembler;


import vip.dcpay.filesystem.domain.constant.CodeEnum;
import vip.dcpay.filesystem.domain.pojo.JsonResult;
import vip.dcpay.vo.basic.Result;


public class ResultAssembler {


    public static JsonResult assembleResult(Result result ){

        JsonResult jsonResult  = new JsonResult<>();

        if(result.getSuccess()){
            jsonResult.setCode(CodeEnum.SUCCESS.getCode());
        }else {
            jsonResult.setCode(CodeEnum.BUSINESS_ERROR.getCode());
        }

        jsonResult.setData(result.getData());
        jsonResult.setMessage(result.getMessage() == null ? CodeEnum.BUSINESS_ERROR.getDesc(): result.getMessage());

        return jsonResult;
    }



}
