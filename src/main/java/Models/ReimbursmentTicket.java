package Models;

import java.sql.Date;

public class ReimbursmentTicket 
{
	public static enum statusFlag
	{
		Pending, Approved, Denied, Invalid;
		
		public static statusFlag getStatusFlag(int input)
		{
			statusFlag flag;
			
			switch(input)
			{
			case 0:
				flag = Pending;
				break;
			case 1:
				flag = Approved;
				break;
			case 2:
				flag = Denied;
				break;
			default:
				flag = Invalid;
				System.out.println("WTF did you do, you done broke it. status enum in ReimTicket");
				break;
			}
			return flag;
		}
		public int getStatusFlag(statusFlag flag)
		{
			int res = -1;
			
			switch(flag)
			{
			case Pending:
				res = 0;
				break;
			case Approved:
				res = 1;
				break;
			case Denied:
				res = 2;
				break;
			default:
				res = -1;
				System.out.println("WTF did you do, you done broke it. status enum in ReimTicket");
				break;
			}
			return res;
		}
		@Override
		public String toString()
		{
			String output = null;
			switch(this)
			{
			case Pending:
				output = "Pending";
				break;
			case Approved:
				output = "Approved";
				break;
			case Denied:
				output = "Denied";
				break;
			default:
				output = "Invalid";
				break;
			}
			
			return output;
		}
		public int getStatusAsInt()
		{
			return getStatusFlag(this);
		}
	}
	private int id;
	private float amount;
	private Date requestDate;
	private Date closeDate;
	private statusFlag status;
	private Employee owner;
	private Manager auditor;
	
	public ReimbursmentTicket(int id, float amount, Date requestDate, int status, Employee ref) {
		super();
		this.id = id;
		this.amount = amount;
		this.requestDate = requestDate;
		this.status = statusFlag.getStatusFlag(status);
		owner = ref;
		auditor = null;
		closeDate = null;
	}
	
	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Manager getAuditor() {
		return auditor;
	}

	public void setAuditor(Manager auditor) {
		this.auditor = auditor;
	}

	public ReimbursmentTicket(int id, float amount, Date requestDate, Date closeDate, int status, Employee ref, Manager mref) {
		this(id, amount, requestDate, status, ref);
		this.closeDate = closeDate;
		auditor = mref;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public statusFlag getStatus() {
		return status;
	}

	public void setStatus(statusFlag status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReimbursmentTicket [id=" + id + ", amount=" + amount + ", requestDate=" + requestDate + ", status="
				+ status + "]";
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

	
	
}
