package cy.ac.ucy.teamc.scc;

public class Cancer {

	int id;
	String name;
	String description;
	String img_name;

	public Cancer(int i, String n, String d, String img) {
		id = i;
		name = n;
		description = d;
		img_name=img;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public String getimg_name() {
		return img_name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

}
