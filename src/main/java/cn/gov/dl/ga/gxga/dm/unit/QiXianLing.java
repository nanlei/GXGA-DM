package cn.gov.dl.ga.gxga.dm.unit;

import java.util.ArrayList;

import cn.gov.dl.ga.gxga.dm.model.Monitor;

/**
 * 七贤岭
 * 
 * @author Nan Lei
 * 
 */
public class QiXianLing {
	private String id_prefix = "qixianling_";
	private ArrayList<Monitor> monitors = new ArrayList<Monitor>();

	public String getId_prefix() {
		return id_prefix;
	}

	public ArrayList<Monitor> getMonitors() {
		return monitors;
	}

	public void setMonitors(ArrayList<Monitor> monitors) {
		this.monitors = monitors;
	}
}
