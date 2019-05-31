<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:forEach items="${result.jobs}" var="job" varStatus="status">
	<tr>
	   <td id="number">
	   		${job.industry}<br>
	   		${job.required_education_level}<br>
	   		${job.experience_level}<br>
	   		${job.expiration_timestamp}
	   	</td>
	   <td id="title">
	   		${job.name}<br>
		   	<strong>${job.title}</strong><br>
	   		키워드&nbsp;:&nbsp;${job.keyword}
	   		<!--  
	   		회사명 &nbsp; ${job.name}<br>
	   		마감일 &nbsp; ${job.expiration_timestamp}<br>
	   		마감유형 &nbsp; ${job.close_type}<br>
	   		지역 &nbsp; ${job.location}<br>
	   		근무시간 &nbsp; ${job.job_time}<br>
	   		산업 &nbsp; ${job.industry}<br>
	   		직종 &nbsp; ${job.job_category}<br>
	   		경력 &nbsp; ${job.experience_level}<br>
	   		학력 &nbsp; ${job.required_education_level}<br>
	   		키워드 &nbsp; ${job.keyword}<br>
	   		연봉 &nbsp; ${job.salary}<br>
	   		-->
	   </td>
	</tr>
</c:forEach>