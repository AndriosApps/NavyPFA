package com.andrios.prt.Classes;

public class Instruction implements Comparable<Instruction>{

	private static final long serialVersionUID = -1538116430747812374L;
    private String url;
    private String icon;
    private String name;
    private String ssic;
    private long updateDate;

	public Instruction(String name, String ssic, String url, long updated){
		this.name = name;
        this.ssic = ssic;
		this.url = url;
        this.updateDate = updated;
	}

    public String getSsic() {
        return ssic;
    }

    public void setSsic(String ssic) {
        this.ssic = ssic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }


    @Override
    public int compareTo(Instruction instruction) {
        if (instruction.getUpdateDate() > updateDate){
            return 1;
        }else if (instruction.getUpdateDate() < updateDate){
            return -1;
        }
        return 0;
    }
}
