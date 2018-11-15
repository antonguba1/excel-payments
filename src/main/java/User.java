import java.util.ArrayList;
import java.util.List;

public class User
{
    private String name;
    private List<Payment> paymentList = new ArrayList<>();

    public User()
    {
    }

    public User(String name, List<Payment> paymentList)
    {
        this.name = name;
        this.paymentList = paymentList;
    }

    public String getName()
    {
        return name;
    }

    public List<Payment> getPaymentList()
    {
        return paymentList;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addPayment(Payment payment)
    {
        paymentList.add(payment);
    }
}
