package sql;

public class EleveBean {

	private long id;
	private String nom;
	private int note;

	public EleveBean() {
		super();
	}

	public EleveBean(String nom, int note) {
		super();
		this.nom = nom;
		this.note = note;
	}

	@Override
	public String toString() {
		return nom + " " + note;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

}
