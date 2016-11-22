package designeditor.editors.dao;

import java.util.ArrayList;
import java.util.List;

import designeditor.editors.bean.Module;
import designeditor.editors.bean.ModuleMethod;
import designeditor.editors.models.TModule;
import designeditor.editors.models.TModuleMethod;
import designeditor.editors.models.TModulePK;
import designeditor.util.DbUtil;

public class ModuleDao {
	
	private ModuleMethodDao moduleMethodDao;
	
	public ModuleDao() {
		moduleMethodDao = new ModuleMethodDao();
	}
	
	public List<TModule> selectAll() {
		TModule tModule = new TModule();
		List<TModule> tModuleList = (List<TModule>) DbUtil.selectAll(tModule);
		return tModuleList;
	}
	
	public Module ModelToBean(TModule module) {
		Module tmpModule = new Module();		
		tmpModule.setComment(module.getComment());
		tmpModule.setModuleId(module.getId().getModuleId());
		tmpModule.setModuleIdName(module.getModuleIdName());
		List<TModuleMethod> tModuleMethodList = moduleMethodDao.selectByFk(module.getId().getProjectId(),module.getId().getPackageId(),module.getId().getModuleId());
		List<ModuleMethod> moduleMethodList = moduleMethodDao.ModelToBean(tModuleMethodList);
		tmpModule.setModuleMethod(moduleMethodList);
		tmpModule.setModuleType(module.getModuleType());
		tmpModule.setPackageId(module.getId().getPackageId());
		tmpModule.setProjectId(module.getId().getProjectId());
		return tmpModule;
	}
	
	public List<Module> ModelToBean(List<TModule> tModuleList) {
		List<Module> moduleList = new ArrayList<Module>();
		for (TModule tModule : tModuleList){
			Module module = ModelToBean(tModule);
			moduleList.add(module);
		}
		return moduleList;
	}
	
	public TModule BeanToModel(Module module) {
		TModule tmpModule = new TModule();
		TModulePK id = new TModulePK();
		id.setModuleId(module.getModuleId());
		id.setPackageId(module.getPackageId());
		id.setProjectId(module.getProjectId());
		tmpModule.setId(id);
		tmpModule.setComment(module.getComment());		
		tmpModule.setModuleIdName(module.getModuleIdName());
		tmpModule.setModuleType(module.getModuleType());
		
		return tmpModule;
	}
	
}
