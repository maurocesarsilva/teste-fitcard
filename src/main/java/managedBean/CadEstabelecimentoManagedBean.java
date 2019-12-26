package managedBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.google.gson.Gson;

import dao.DaoEstabelecimento;
import dao.DaoGeneric;
import model.CadCat;
import model.CadEstabelecimento;

@ManagedBean(name = "cadEstabelecimentoBean")
@ViewScoped
public class CadEstabelecimentoManagedBean {

	CadEstabelecimento cadE = new CadEstabelecimento();
	private DaoGeneric<CadEstabelecimento> daoGeneric = new DaoGeneric<CadEstabelecimento>();
	private DaoEstabelecimento<CadEstabelecimento> daoEst = new DaoEstabelecimento<CadEstabelecimento>();
	private List<CadEstabelecimento> list = new ArrayList<CadEstabelecimento>();
	private DaoGeneric<CadCat> daoCadCat = new DaoGeneric<CadCat>();
	private List<CadCat> cat = new ArrayList<CadCat>();


	public String salvar() {
		Long i = cadE.getId();

		if (cadE.getCategoria().equalsIgnoreCase("Supermercado") && cadE.getTelefone() == "") {
			mostrarMsg("Inserir Telefone!", 'f');
		} else {

				if (i == 0) {
					if (buscaPorCnpj() == false) {

					} else {
					cadE = daoGeneric.updateMerge(cadE);

					init();
					novo();
					mostrarMsg("Cadastrado com sucesso", 'i');
					}
				} else {
					{
						cadE = daoGeneric.updateMerge(cadE);

						init();
						novo();
						mostrarMsg("Alterado com sucesso", 'i');
					}
				}

			

		}
		return "";
	}

	public String remove() {
		try {
			daoGeneric.deletarPorId(cadE);
			cadE = new CadEstabelecimento();
			init();
			novo();
			mostrarMsg("Removido com sucesso", 'f');

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";

	}

	@PostConstruct
	public void init() {

		list.clear();
		list = daoGeneric.listar(CadEstabelecimento.class);
		if (list.isEmpty()) {
			mostrarMsg("Nenhum estabelecimento encontrado!", 'f');
		}
		novo();
	}

	public boolean buscaPorCnpj() {
		
		list.clear();
		list = daoEst.listarPorCnpj(CadEstabelecimento.class, cadE.getCnpj());
		if (list.isEmpty()) {

			return true;
		} else {
			mostrarMsg("Cnpj já cadastrado!", 'f');
		}

		return false;
	}
	
	public void buscaPorRazao() {

		list.clear();
		list = daoEst.listarPorRazao(CadEstabelecimento.class, cadE.getRazaoS());
		if (list.isEmpty()) {
			mostrarMsg("Nenhum estabelecimento encontrado!", 'f');
		}

		novo();
	}

	public String novo() {
		cadE = new CadEstabelecimento();

		return "";
	}

	private void mostrarMsg(String msg, char tipo) {

		if (tipo == 'f') {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
		} else if (tipo == 'i') {

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(msg);
			context.addMessage(null, message);
		} else {
		}

	}

	// web service via cep
	public void pesquisaCep(AjaxBehaviorEvent event) {
		try {
			URL url = new URL("https://viacep.com.br/ws/" + cadE.getCep() + "/json/");

			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String cep = "";
			StringBuilder jsonCep = new StringBuilder();

			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}

			CadEstabelecimento gsonAux = new Gson().fromJson(jsonCep.toString(), CadEstabelecimento.class);

			System.out.println("" + gsonAux);

			cadE.setCep(gsonAux.getCep());
			cadE.setLogradouro(gsonAux.getLogradouro());
			cadE.setComplemento(gsonAux.getComplemento());
			cadE.setBairro(gsonAux.getBairro());
			cadE.setLocalidade(gsonAux.getLocalidade());
			cadE.setUf(gsonAux.getUf());

		} catch (Exception ex) {
			ex.printStackTrace();
			mostrarMsg("Erro ao consultar o cep", 'f');
		}

	}

	/* getters e setters */
	public CadEstabelecimento getCadE() {
		return cadE;
	}

	public void setCadE(CadEstabelecimento cadE) {
		this.cadE = cadE;
	}

	public List<CadEstabelecimento> getList() {
		return list;
	}

	public List<CadCat> getCat() {
		cat.clear();
		cat = daoCadCat.listar(CadCat.class);

		return cat;
	}

}
