package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DaoGeneric;
import model.CadCat;
import model.CadEstabelecimento;

@ManagedBean(name = "cadCatBean")
@ViewScoped
public class CadCatManagedBean {

	CadCat cadCat = new CadCat();
	private DaoGeneric<CadCat> daoCadCat = new DaoGeneric<CadCat>();
	private List<CadCat> list = new ArrayList<CadCat>();
	
	@PostConstruct 
	public void init() {
		list.clear();
		list = daoCadCat.listar(CadCat.class); 
	}

	public String salvar() {
		Long a = cadCat.getId();
		cadCat = daoCadCat.updateMerge(cadCat);
		
		
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", "Salvo com sucesso!"));

		

		novo();
		init();
		
		return "";
	}

	public String remove() {
		try {
			daoCadCat.deletarPorId(cadCat);
			cadCat = new CadCat();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Removido com sucesso!", "Removido com sucesso!"));
			novo();
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";

	}

	public String novo() {
		cadCat = new CadCat();

		return "";
	}

	/* getters e setters */
	public CadCat getCadCat() {
		return cadCat;
	}

	public void setCadCat(CadCat cadCat) {
		this.cadCat = cadCat;
	}
	
	public List<CadCat> getList() {
		return list;
	}
}