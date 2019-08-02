package com.cnk.siapi.productstatic.response;

public class CustomerReviews
{
    private String author;

    private String title;

    private String source;

    private String score;

    private String comment;

    private String date;

    private String type;

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

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

    public String getComment ()
    {
        return comment;
    }

    public void setComment (String comment)
    {
        this.comment = comment;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
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
        return "ClassPojo [author = "+author+", title = "+title+", source = "+source+", score = "+score+", comment = "+comment+", date = "+date+", type = "+type+"]";
    }
}
			
		