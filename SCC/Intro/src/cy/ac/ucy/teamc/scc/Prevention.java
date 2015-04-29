package cy.ac.ucy.teamc.scc;

public class Prevention {
	int id;
	String name;
	String description;
	String image_name;
	public Prevention(int i, String n, String d, String img) {
		id = i;
		name = n;
		description = d;
		image_name=img;
	}

	public int getId() {
		return id;
	}
	public String getimage_name() {
		return image_name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
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
}
