package cy.ac.ucy.teamc.scc;

import java.io.Serializable;

public class Exam  implements Serializable {
	int id;
private String name;
private String age_range;
private int smoker;
private int alcohol;
private String deiktis_mazas_range;
private int gender;
private int inheritance;
private int SexualSituation;
private String description;
private String image_name;
private int frequency;
private int type;//0 if is exam , 1 if is revention 

public Exam(int id,String name,String age_range,int smoker,int alcohol,String deiktis_mazas_range,int gender,int inheritance, int SexualSituation, String  description, String image_name, int frequency, int type)
{
	this.id=id;
	this.name=name;
	this.age_range=age_range;
	this.alcohol=alcohol;
	this.deiktis_mazas_range=deiktis_mazas_range;
	this.inheritance=inheritance;
	this.smoker=smoker;
	this.gender=gender;
	this.description=description;
	this.SexualSituation=SexualSituation;
	this.image_name=image_name;
	this.frequency=frequency;
	this.type=type;
}

public int get_id()
{
	return this.id;
}
public int get_type()
{
	return this.type;
}
public int get_frequency()
{
	return this.frequency;
}
public String get_image_name()
{
	return this.image_name;
}
public String get_name()
{
	return this.name;
}
public String get_description()
{
	return this.description;
}
public String get_age_range()
{
	return this.age_range;
}

public int get_alcohol()
{
	return this.alcohol;
}

public String get_deiktis_mazas_range()
{
	return this.deiktis_mazas_range;
}

public int get_inheritance()
{
	return this.inheritance;
}

public int get_smoker()
{
	return this.smoker;
}

public int get_gender()
{
	return this.gender;
}


public int get_SexualSituation()
{
	return this.SexualSituation;
}


public void set_name(String name)
{
	this.name=name;
}
public void set_description(String description)
{
	this.description=description;
}
public void set_age_range(String age_range)
{
	this.age_range=age_range;
}

public void set_alcohol(int alcohol)
{
	this.alcohol=alcohol;
}

public void set_deiktis_mazas_range(String deiktis_mazas_range)
{
	this.deiktis_mazas_range=deiktis_mazas_range;
}

public void set_inheritance(int inheritance)
{
	this.inheritance=inheritance;
}

public void set_smoker(int smoker)
{
	this.smoker=smoker;
}

public void set_gender(int gender)
{
	this.gender=gender;
}

public void set_SexualSituation(int SexualSituation)
{
	this.SexualSituation=SexualSituation;
}

public void set_image_name(String image_name)
{
	this.image_name=image_name;
}

public void set_frequency(int frequency)
{
	this.frequency=frequency;
}
}
