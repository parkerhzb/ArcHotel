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

import com.arcsoft.hotel.pojo.CheckIn;
import com.arcsoft.hotel.pojo.CheckInExample.Criteria;
import com.arcsoft.hotel.pojo.CheckInExample.Criterion;
import com.arcsoft.hotel.pojo.CheckInExample;
import java.util.List;
import java.util.Map;

public class CheckInSqlProvider {

    public String countByExample(CheckInExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("check_in");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(CheckInExample example) {
        BEGIN();
        DELETE_FROM("check_in");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(CheckIn record) {
        BEGIN();
        INSERT_INTO("check_in");

        if (record.getCheckinDate() != null) {
            VALUES("checkin_date", "#{checkinDate,jdbcType=DATE}");
        }

        if (record.getCheckoutDate() != null) {
            VALUES("checkout_date", "#{checkoutDate,jdbcType=DATE}");
        }

        if (record.getTime() != null) {
            VALUES("time", "#{time,jdbcType=VARCHAR}");
        }

        if (record.getRoomId() != null) {
            VALUES("room_id", "#{roomId,jdbcType=INTEGER}");
        }

        if (record.getRelatedRoom() != null) {
            VALUES("related_room", "#{relatedRoom,jdbcType=VARCHAR}");
        }

        if (record.getPersonNum() != null) {
            VALUES("person_num", "#{personNum,jdbcType=INTEGER}");
        }
        
        if (record.getIsCheckOut() != null) {
            VALUES("is_check_out", "#{isCheckOut,jdbcType=TINYINT}");
        }

        return SQL();
    }

    public String selectByExample(CheckInExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("checkin_date");
        SELECT("checkout_date");
        SELECT("time");
        SELECT("room_id");
        SELECT("related_room");
        SELECT("person_num");
        SELECT("is_check_out");
        FROM("check_in");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CheckIn record = (CheckIn) parameter.get("record");
        CheckInExample example = (CheckInExample) parameter.get("example");

        BEGIN();
        UPDATE("check_in");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getCheckinDate() != null) {
            SET("checkin_date = #{record.checkinDate,jdbcType=DATE}");
        }

        if (record.getCheckoutDate() != null) {
            SET("checkout_date = #{record.checkoutDate,jdbcType=DATE}");
        }

        if (record.getTime() != null) {
            SET("time = #{record.time,jdbcType=VARCHAR}");
        }

        if (record.getRoomId() != null) {
            SET("room_id = #{record.roomId,jdbcType=INTEGER}");
        }

        if (record.getRelatedRoom() != null) {
            SET("related_room = #{record.relatedRoom,jdbcType=VARCHAR}");
        }

        if (record.getPersonNum() != null) {
            SET("person_num = #{record.personNum,jdbcType=INTEGER}");
        }
        
        if (record.getIsCheckOut() != null) {
            SET("is_check_out = #{record.isCheckOut,jdbcType=TINYINT}");
        }

        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("check_in");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("checkin_date = #{record.checkinDate,jdbcType=DATE}");
        SET("checkout_date = #{record.checkoutDate,jdbcType=DATE}");
        SET("time = #{record.time,jdbcType=VARCHAR}");
        SET("room_id = #{record.roomId,jdbcType=INTEGER}");
        SET("related_room = #{record.relatedRoom,jdbcType=VARCHAR}");
        SET("person_num = #{record.personNum,jdbcType=INTEGER}");
        SET("is_check_out = #{record.isCheckOut,jdbcType=TINYINT}");

        CheckInExample example = (CheckInExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(CheckIn record) {
        BEGIN();
        UPDATE("check_in");

        if (record.getCheckinDate() != null) {
            SET("checkin_date = #{checkinDate,jdbcType=DATE}");
        }

        if (record.getCheckoutDate() != null) {
            SET("checkout_date = #{checkoutDate,jdbcType=DATE}");
        }

        if (record.getTime() != null) {
            SET("time = #{time,jdbcType=VARCHAR}");
        }

        if (record.getRoomId() != null) {
            SET("room_id = #{roomId,jdbcType=INTEGER}");
        }

        if (record.getRelatedRoom() != null) {
            SET("related_room = #{relatedRoom,jdbcType=VARCHAR}");
        }

        if (record.getPersonNum() != null) {
            SET("person_num = #{personNum,jdbcType=INTEGER}");
        }
        
        if (record.getIsCheckOut() != null) {
            SET("is_check_out = #{isCheckOut,jdbcType=TINYINT}");
        }

        WHERE("id = #{id,jdbcType=INTEGER}");

        return SQL();
    }

    protected void applyWhere(CheckInExample example, boolean includeExamplePhrase) {
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