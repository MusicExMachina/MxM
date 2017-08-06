package time;

public interface ITime {
    public ITime plus(ITime other);
    public ITime minus(ITime other);
    public ITime times(int amount);
    public ITime dividedBy(int amount);
}
