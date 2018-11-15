import java.util.Date;

public class Payment
{
    private Date dueDate;
    private Date actualDate;

    public Payment(Date dueDate, Date actualDate)
    {
        this.dueDate = dueDate;
        this.actualDate = actualDate;
    }

    public Payment()
    {
    }

    public Date getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    public void setActualDate(Date actualDate)
    {
        this.actualDate = actualDate;
    }
}
