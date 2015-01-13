package ch.janicmikes.id3audiotagmanager;

public abstract class Setting {
	    protected abstract boolean TryParseValues();

	    protected abstract boolean CheckValues();

	    public abstract void SetDefaultValues();

	    /// <summary>
	    /// Template Method
	    /// </summary>
	    public boolean TrySetValuesOrDefault()
	    {
	        if (!TryParseValues() || !CheckValues())
	        {
	            // parsing error or domain error
	            SetDefaultValues();
	            return false;
	        }
	        return true;
	    }
}
