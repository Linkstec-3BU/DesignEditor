package designeditor.editors.dao;

import java.util.ArrayList;
import java.util.List;

import designeditor.editors.bean.MethodDesign;
import designeditor.editors.bean.ModuleMethod;
import designeditor.editors.models.TMethodParameter;
import designeditor.editors.models.TModuleMethod;
import designeditor.editors.models.TModuleMethodPK;
import designeditor.util.DbUtil;

public class ModuleMethodDao {

	private MethodParameterDao methodParameterDao;

	public ModuleMethodDao() {
		methodParameterDao = new MethodParameterDao();
	}

	public List<TModuleMethod> selectByFk(String projectId, String packageId, String moduleId) {
		String sql = "select m from TModuleMethod m where m.id.projectId = ?1 and m.id.packageId = ?2 and m.id.moduleId = ?3";
		String[] parameters = { projectId, packageId, moduleId };
		List<TModuleMethod> tModuleMethodList = (List<TModuleMethod>) DbUtil.selectByOthers(sql, parameters);
		return tModuleMethodList;
	}

	public ModuleMethod ModelToBean(TModuleMethod tModuleMethod) {
		ModuleMethod moduleMethod = new ModuleMethod();
		moduleMethod.setComment(tModuleMethod.getComment());
		moduleMethod.setProjectId(tModuleMethod.getId().getProjectId());
		moduleMethod.setPackageId(tModuleMethod.getId().getPackageId());
		moduleMethod.setModuleId(tModuleMethod.getId().getModuleId());
		moduleMethod.setMethodId(tModuleMethod.getId().getMethodId());
		// moduleMethod.setMethodDesignList(new MethodDesign("", "", "", ""));
		moduleMethod.setMethodIdName(tModuleMethod.getMethodIdName());

		List<TMethodParameter> tMethodParameterList = methodParameterDao.selectByFk(tModuleMethod.getId().getProjectId(),
				tModuleMethod.getId().getPackageId(), tModuleMethod.getId().getModuleId(),
				tModuleMethod.getId().getMethodId());
		moduleMethod.setMethodParameter(methodParameterDao.ModelToBean(tMethodParameterList));
		moduleMethod.setMethodReturnType(tModuleMethod.getMethodReturnType());
		moduleMethod.setMethodThrows1(tModuleMethod.getMethodThrows1());
		moduleMethod.setMethodUniqueId(tModuleMethod.getMethodUniqueId());
		return moduleMethod;
	}

	public List<ModuleMethod> ModelToBean(List<TModuleMethod> tModuleMethodList) {
		List<ModuleMethod> moduleMethodList = new ArrayList<ModuleMethod>();
		for (TModuleMethod tModuleMethod : tModuleMethodList) {
			ModuleMethod moduleMethod = ModelToBean(tModuleMethod);
			moduleMethodList.add(moduleMethod);
		}
		return moduleMethodList;
	}

	public TModuleMethod BeanToModel(ModuleMethod moduleMethod) {
		TModuleMethod tModuleMethod = new TModuleMethod();
		tModuleMethod.setMethodIdName(moduleMethod.getMethodIdName());
		tModuleMethod.setComment(moduleMethod.getComment());
		tModuleMethod.setMethodReturnType(moduleMethod.getMethodReturnType());
		tModuleMethod.setMethodThrows1(moduleMethod.getMethodThrows1());
		tModuleMethod.setMethodUniqueId(moduleMethod.getMethodUniqueId());
		TModuleMethodPK id = new TModuleMethodPK();
		id.setProjectId(moduleMethod.getProjectId());
		id.setPackageId(moduleMethod.getPackageId());
		id.setMethodId(moduleMethod.getMethodId());
		id.setModuleId(moduleMethod.getModuleId());
		tModuleMethod.setId(id);
		return tModuleMethod;
	}

	public List<TModuleMethod> BeanToModel(List<ModuleMethod> moduleMethodList) {
		List<TModuleMethod> tModuleMethodList = new ArrayList<TModuleMethod>();
		for (ModuleMethod moduleMethod : moduleMethodList) {
			TModuleMethod tModuleMethod = BeanToModel(moduleMethod);
			tModuleMethodList.add(tModuleMethod);
		}
		return tModuleMethodList;
	}
}
