package com.yonlabs.jpa.puzzles.config;

import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SQLCountingListener implements QueryExecutionListener {

    private int selectCount;

    private int insertCount;

    private int updateCount;

    private int deleteCount;

    private int otherCount;

    @Override
    public void beforeQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        queryInfoList.forEach(this::countQuery);
     }

    @Override
    public void afterQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {

    }

    private void countQuery(QueryInfo queryInfo) {
        String query = queryInfo.getQuery();
        if (isOperation(query, "select")) selectCount++;
        else if (isOperation(query, "insert")) insertCount++;
        else if (isOperation(query, "update")) updateCount++;
        else if (isOperation(query, "delete")) deleteCount++;
        else otherCount++;
    }

    private boolean isOperation(String query, String type) {
        return query.startsWith(type);
    }

    public int getSelectCount() {
        return selectCount;
    }

    public int getInsertCount() {
        return insertCount;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public int getDeleteCount() {
        return deleteCount;
    }

    public int getOtherCount() {
        return otherCount;
    }

    public void reset() {
        selectCount = 0;
        insertCount = 0;
        updateCount = 0;
        deleteCount = 0;
        otherCount = 0;
    }

    @Override
    public String toString() {
        return "SQLCountingListener{" +
                "selectCount=" + selectCount +
                ", insertCount=" + insertCount +
                ", updateCount=" + updateCount +
                ", deleteCount=" + deleteCount +
                ", otherCount=" + otherCount +
                '}';
    }

}
