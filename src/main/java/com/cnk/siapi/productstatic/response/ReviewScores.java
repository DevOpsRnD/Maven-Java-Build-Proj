package com.cnk.siapi.productstatic.response;


public class ReviewScores
{
private String source;

private String score;

private String type;

public String getSource ()
{
return source;
}

public void setSource (String source)
{
this.source = source;
}

public String getScore ()
{
return score;
}

public void setScore (String score)
{
this.score = score;
}

public String getType ()
{
return type;
}

public void setType (String type)
{
this.type = type;
}

@Override
public String toString()
{
return "ClassPojo [source = "+source+", score = "+score+", type = "+type+"]";
}
}

