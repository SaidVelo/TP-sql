package sql;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Insertion
//		EleveBean eleve = new EleveBean("Toto2", 14);
//		try {
//			EleveDaoUtil.insertEleve(eleve);
//			System.out.println("Eleve bien inseré");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// Select
		try {
			ArrayList<EleveBean> tabEleve = EleveDaoUtil.getAllEleve();

			for (EleveBean eleveBean : tabEleve) {
				System.out.println(eleveBean.getId() + " " + eleveBean.getNom() + " " + eleveBean.getNote());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("terminé");

	}

}
