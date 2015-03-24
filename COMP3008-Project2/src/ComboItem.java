/**
 * 
 * @deprecated
 */
class ComboItem
{
    private String key;
    private String value;
    /**
     * @deprecated
     */
    public ComboItem(String key, String value)
    {
        this.key = key;
        this.value = value;
    }
    /**
     * @deprecated
     */
    @Override
    public String toString()
    {
        return key;
    }
    /**
     * @deprecated
     */
    public String getKey()
    {
        return key;
    }
    /**
     * @deprecated
     */
    public String getValue()
    {
        return value;
    }
}