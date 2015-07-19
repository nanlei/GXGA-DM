package cn.gov.dl.ga.gxga.dm.model;

/**
 * 监控
 * 
 * @author Nan Lei
 * 
 */
public class Monitor {
	private String id;
	private String area;
	private String lng;
	private String lat;
	private String name;
	private String status;
	private String remark;

	public Monitor() {
		super();
	}

	public Monitor(String id, String area, String lng, String lat, String name,
			String status, String remark) {
		super();
		this.id = id;
		this.area = area;
		this.lng = lng;
		this.lat = lat;
		this.name = name;
		this.status = status;
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Monitor [id=" + id + ", area=" + area + ", lng=" + lng
				+ ", lat=" + lat + ", name=" + name + ", status=" + status
				+ ", remark=" + remark + "]";
	}

}
