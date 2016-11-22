package designeditor.editors.dao;

import java.util.ArrayList;
import java.util.List;

import designeditor.editors.bean.MethodParameter;
import designeditor.editors.models.TMethodParameter;
import designeditor.editors.models.TMethodParameterPK;
import designeditor.util.DbUtil;

public class MethodParameterDao {
	public List<TMethodParameter> selectByFk(String projectId, String packageId, String moduleId, String methodId) {
		List<TMethodParameter> tMethodParameterList = new ArrayList<TMethodParameter>();
		String sql = "select p from TMethodParameter p where p.id.projectId = ?1 and p.id.packageId = ?2 and p.id.moduleId = ?3 and p.id.methodId = ?4";
		String[] parameters = { projectId, packageId, moduleId, methodId };
		tMethodParameterList = (List<TMethodParameter>) DbUtil.selectByOthers(sql, parameters);
		return tMethodParameterList;
	}
	
	public MethodParameter ModelToBean(TMethodParameter tMethodParameter) {
		MethodParameter methodParameter = new MethodParameter();
		methodParameter.setComment(tMethodParameter.getComment());
		methodParameter.setMethodId(tMethodParameter.getId().getMethodId());
		methodParameter.setModuleId(tMethodParameter.getId().getModuleId());
		methodParameter.setProjectId(tMethodParameter.getId().getProjectId());
		methodParameter.setPackageId(tMethodParameter.getId().getPackageId());
		methodParameter.setParameterName(tMethodParameter.getParameterName());
		methodParameter.setParamterType(tMethodParameter.getParamterType());
		methodParameter.setParameterId(tMethodParameter.getId().getParameterId());
		return methodParameter;
	}
	
	public List<MethodParameter> ModelToBean(List<TMethodParameter> tMethodParameterList) {
		List<MethodParameter> methodParameterList = new ArrayList<MethodParameter>();
		for (TMethodParameter tMethodParameter : tMethodParameterList){
			MethodParameter methodParameter = ModelToBean(tMethodParameter);
			methodParameterList.add(methodParameter);
		}
		return methodParameterList;		
	}
	
	public TMethodParameter BeanToModel(MethodParameter methodParameter) {
		TMethodParameter tMethodParameter = new TMethodParameter();
		tMethodParameter.setComment(methodParameter.getComment());
		tMethodParameter.setParameterName(methodParameter.getParameterName());
		tMethodParameter.setParamterType(methodParameter.getParamterType());
		TMethodParameterPK id = new TMethodParameterPK();
		id.setMethodId(methodParameter.getMethodId());
		id.setModuleId(methodParameter.getModuleId());
		id.setPackageId(methodParameter.getPackageId());
		id.setProjectId(methodParameter.getProjectId());
		id.setParameterId(methodParameter.getParameterId());
		tMethodParameter.setId(id);
		return tMethodParameter;
	}
}
