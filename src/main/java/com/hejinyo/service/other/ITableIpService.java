package com.hejinyo.service.other;


import com.hejinyo.common.PageParam;
import com.hejinyo.model.other.TableIp;

public interface ITableIpService {

	public int getRowCount();
	public PageParam getIpListByPage(PageParam pageParam);
	public String printIp(String number, String country, String isp);

    public int addTable_Ip(TableIp tableIp);
}
