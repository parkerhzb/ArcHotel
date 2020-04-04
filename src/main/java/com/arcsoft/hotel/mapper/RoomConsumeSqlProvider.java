package com.arcsoft.hotel.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.arcsoft.hotel.pojo.RoomConsume;
import com.arcsoft.hotel.pojo.RoomConsumeExample.Criteria;
import com.arcsoft.hotel.pojo.RoomConsumeExample.Criterion;
import com.arcsoft.hotel.pojo.RoomConsumeExample;
import java.util.List;
import java.util.Map;

public class RoomConsumeSqlProvider {

    public String countByExample(RoomConsumeExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("room_consume");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(RoomConsumeExample example) {
        BEGIN();
        DELETE_FROM("room_consume");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(RoomConsume record) {
        BEGIN();
        INSERT_INTO("room_consume");

        if (record.getRoomId() != null) {
            VALUES("room_id", "#{roomId,jdbcType=INTEGER}");
        }

        if (record.getTime() != null) {
            VALUES("time", "#{time,jdbcType=TIMESTAMP}");
        }

        if (record.getItem() != null) {
            VALUES("item", "#{item,jdbcType=VARCHAR}");
        }

        if (record.getPrice() != null) {
            VALUES("price", "#{price,jdbcType=DOUBLE}");
        }

        return SQL();
    }

    public String selectByExample(RoomConsumeExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("room_id");
        SELECT("time");
        SELECT("item");
        SELECT("price");
        FROM("room_consume");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        RoomConsume record = (RoomConsume) parameter.get("record");
        RoomConsumeExample example = (RoomConsumeExample) parameter.get("example");

        BEGIN();
        UPDATE("room_consume");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getRoomId() != null) {
            SET("room_id = #{record.roomId,jdbcType=INTEGER}");
        }

        if (record.getTime() != null) {
            SET("time = #{record.time,jdbcType=TIMESTAMP}");
        }

        if (record.getItem() != null) {
            SET("item = #{record.item,jdbcType=VARCHAR}");
        }

        if (record.getPrice() != null) {
            SET("price = #{record.price,jdbcType=DOUBLE}");
        }

        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("room_consume");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("room_id = #{record.roomId,jdbcType=INTEGER}");
        SET("time = #{record.time,jdbcType=TIMESTAMP}");
        SET("item = #{record.item,jdbcType=VARCHAR}");
        SET("price = #{record.price,jdbcType=DOUBLE}");

        RoomConsumeExample example = (RoomConsumeExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(RoomConsume record) {
        BEGIN();
        UPDATE("room_consume");

        if (record.getRoomId() != null) {
            SET("room_id = #{roomId,jdbcType=INTEGER}");
        }

        if (record.getTime() != null) {
            SET("time = #{time,jdbcType=TIMESTAMP}");
        }

        if (record.getItem() != null) {
            SET("item = #{item,jdbcType=VARCHAR}");
        }

        if (record.getPrice() != null) {
            SET("price = #{price,jdbcType=DOUBLE}");
        }

        WHERE("id = #{id,jdbcType=INTEGER}");

        return SQL();
    }

    protected void applyWhere(RoomConsumeExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }

        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }

        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }

                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }

                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }

        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}