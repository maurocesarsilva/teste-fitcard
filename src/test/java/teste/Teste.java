package teste;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dao.DaoEstabelecimento;
import dao.DaoGeneric;
import model.CadCat;
import model.CadEstabelecimento;

public class Teste {

	CadEstabelecimento cadE = new CadEstabelecimento();
	private DaoGeneric<CadEstabelecimento> daoGeneric = new DaoGeneric<CadEstabelecimento>();
	private DaoEstabelecimento<CadEstabelecimento> daoEst = new DaoEstabelecimento<CadEstabelecimento>();
	private List<CadEstabelecimento> list = new ArrayList<CadEstabelecimento>();
	private DaoGeneric<CadCat> daoCadCat = new DaoGeneric<CadCat>();
	private List<CadCat> cat = new ArrayList<CadCat>();

	// teste de busca por cnpj
	@Test
	public void buscaPorCnpj() {

		list.clear();
		list = daoEst.listarPorRazao(CadEstabelecimento.class, cadE.getCnpj());

		for (CadEstabelecimento cadEstabelecimento : list) {
			System.out.println("nomes: " + cadEstabelecimento.getCnpj());
		}

	}

	// teste de busca por cnpj
	@Test
	public void buscaPorRazao() {

		list.clear();
		list = daoEst.listarPorRazao(CadEstabelecimento.class, "mauro");

		for (CadEstabelecimento cadEstabelecimento : list) {
			System.out.println("nomes: " + cadEstabelecimento.getRazaoS());
		}
	}

	//teste inserção e atualização
	@Test
	public void salvar() {

		cadE.setRazaoS("teste");
		cadE.setCnpj("49.742.087/0001-61");
		
		cadE = daoGeneric.updateMerge(cadE);
		System.out.println("inserido ");

	}
	
}		
