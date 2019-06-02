<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <style>
    
    body {
    background-color: #BDBDBD;
}
        *{
            text-align: left;
            
        }       
        .Content{
       		width : 80%;
       		overflow:hidden;
			height:auto;
            line-height: 40px;
            border : 0px solid green;
            border-radius: 20px / 20px;
            margin : auto;
            background-color: #FFFFFF;
        }
    	.Part{
    		padding : 5%;
    		width : 50%;
    		float : left;
    	}
    </style>
</head>

<body>
<c:if test="${!empty result.JobP}">
	<br>
	<div class="Content">
		<div class="Part">
			<a href="${result.JobP.url}"}>잡플래닛</a><br>
			${result.JobP.CompName}<br>
			${result.JobP.totalReview}<br>
			총점&nbsp;${result.JobP.score}<br>
			
		</div>
		<div class="Part">
			${result.JobP.score0}&nbsp;${result.JobP.scoreVal0}<br>
			${result.JobP.score1}&nbsp;${result.JobP.scoreVal1}<br>
			${result.JobP.score2}&nbsp;${result.JobP.scoreVal2}<br>
			${result.JobP.score3}&nbsp;${result.JobP.scoreVal3}<br>
			${result.JobP.score4}&nbsp;${result.JobP.scoreVal4}<br>
		</div>
	</div>
</c:if>

<c:if test="${!empty result.Indeed}">
	<br>
	<div class="Content">
		<div class="Part">
			<a href="${result.Indeed.url}"}>인디드</a><br>
			${result.Indeed.CompName}
		</div>
		<div class="Part">
			${result.Indeed.score0}&nbsp;${result.Indeed.scoreVal0}<br>
			${result.Indeed.score1}&nbsp;${result.Indeed.scoreVal1}<br>
			${result.Indeed.score2}&nbsp;${result.Indeed.scoreVal2}<br>
			${result.Indeed.score3}&nbsp;${result.Indeed.scoreVal3}<br>
			${result.Indeed.score4}&nbsp;${result.Indeed.scoreVal4}<br>
		</div>
	</div>
</c:if>

<c:if test="${!empty result.KreditJ}">
	<br>
	<div class="Content">
		<div class="Part">
			<a href="${result.KreditJ.url}"}>크레딧잡</a><br>
			업데이트&nbsp;${result.KreditJ.DATA_YM_MAX}<br>
			${result.Indeed.CompName}<br>
			평균연봉-국민연금&nbsp;${Double.parseDouble(result.KreditJ.AVG_SALARY_YY).longValue()}<br>
			평균연봉-고용보험&nbsp;${result.KreditJ.SALARY_KCOM}<br>
			<br>
			소재지&nbsp;${result.KreditJ.WKP_ADRS}<br>
			산업군&nbsp;${result.KreditJ.STNDD_BIG_GB_NM}<br>
			기업구분&nbsp;${result.KreditJ.CMPN_GB_CLASS}<br>
			<br>
			총 매출액-${result.KreditJ.BASE_YY}년&nbsp;${result.KreditJ.SALES_VALUE}<br>
			1인당 매출액&nbsp;${result.KreditJ.SALES_PER_MAN}<br>
			매출대비임금비율&nbsp;${result.KreditJ.SALARY_SALES_RATIO}%<br>
		</div>
		<div class="Part">
			인원&nbsp;${result.KreditJ.PRSN_BASE}<br>
			동종산업인원&nbsp;${result.KreditJ.AVG_PRSN_STNDD}<br>
			전체기업인원&nbsp;${result.KreditJ.AVG_PRSN_TOTAL}<br>
			<br>
			업력&nbsp;${result.KreditJ.COMPANY_AGE}<br>
			동종산업업력&nbsp;${result.KreditJ.AVG_AGE_STNDD}<br>
			전체기업업력&nbsp;${result.KreditJ.AVG_AGE_TOTAL}<br>
			<br>
			입사율&nbsp;${result.KreditJ.PRSN_IN_RATIO}<br>
			입사자&nbsp;${result.KreditJ.PRSN_IN_BASE}<br>
			동종산업입사자&nbsp;${result.KreditJ.AVG_IN_STNDD}<br>
			전체기업입사자&nbsp;${result.KreditJ.AVG_IN_TOTAL}<br>
			<br>
			퇴사율&nbsp;${result.KreditJ.PRSN_OUT_RATIO}<br>
			퇴사자&nbsp;${result.KreditJ.PRSN_OUT_BASE}<br>
			동종산업퇴사자&nbsp;${result.KreditJ.AVG_OUT_STNDD}<br>
			전체기업퇴사자&nbsp;${result.KreditJ.AVG_OUT_TOTAL}<br>
			<br>
			올해입사자 평균연봉-${result.KreditJ.SALARY_FROM}&nbsp;${result.KreditJ.ROOKEY_SALARY_FINAL}<br>
			동종산업신입연봉&nbsp;${Double.parseDouble(result.KreditJ.AVG_ROOKEY_SALARY_STNDD).longValue()}<br>
			전체기업신입연봉&nbsp;${Double.parseDouble(result.KreditJ.AVG_ROOKEY_SALARY_TOTAL).longValue()}<br>
			<br>
			평균연봉-${result.KreditJ.SALARY_FROM}&nbsp;${result.KreditJ.AVG_SALARY_FSS}<br>
			동종산업연봉&nbsp;${Double.parseDouble(result.KreditJ.AVG_SALARY_STNDD).longValue()}<br>
			전체기업평균연봉&nbsp;${Double.parseDouble(result.KreditJ.AVG_SALARY_TOTAL).longValue()}<br>
		</div>
	</div>
 </c:if>
</body>
</html>