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

import com.arcsoft.hotel.pojo.UserReserve;
import com.arcsoft.hotel.pojo.UserReserveExample.Criteria;
import com.arcsoft.hotel.pojo.UserReserveExample.Criterion;
import com.arcsoft.hotel.pojo.UserReserveExample;
import java.util.List;
import java.util.Map;

public class UserReserveSqlProvider {

    public String countByExample(UserReserveExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("user_reserve");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(UserReserveExample example) {
        BEGIN();
        DELETE_FROM("user_reserve");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(UserReserve record) {
        BEGIN();
        INSERT_INTO("user_reserve");

        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getCheckinDate() != null) {
            VALUES("checkin_date", "#{checkinDate,jdbcType=DATE}");
        }

        if (record.getCheckoutDate() != null) {
            VALUES("checkout_date", "#{checkoutDate,jdbcType=DATE}");
        }

        if (record.getRoomType() != null) {
            VALUES("room_type", "#{roomType,jdbcType=VARCHAR}");
        }

        if (record.getTime() != null) {
            VALUES("time", "#{time,jdbcType=TIMESTAMP}");
        }

        if (record.getPhoneNumber() != null) {
            VALUES("phone_number", "#{phoneNumber,jdbcType=VARCHAR}");
        }

        if (record.getNum() != null) {
            VALUES("num", "#{num,jdbcType=INTEGER}");
        }

        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=INTEGER}");
        }

        if (record.getCheckinId() != null) {
            VALUES("checkin_id", "#{checkinId,jdbcType=INTEGER}");
        }
        
        if (record.getFace() != null) {
            VALUES("face", "#{face,jdbcType=VARBINARY}");
        }

        return SQL();
    }

    public String selectByExampleWithBLOBs(UserReserveExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("checkin_date");
        SELECT("checkout_date");
        SELECT("room_type");
        SELECT("time");
        SELECT("phone_number");
        SELECT("num");
        SELECT("user_id");
        SELECT("status");
        SELECT("checkin_id");
        SELECT("face");
        FROM("user_reserve");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String selectByExample(UserReserveExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("checkin_date");
        SELECT("checkout_date");
        SELECT("room_type");
        SELECT("time");
        SELECT("phone_number");
        SELECT("num");
        SELECT("user_id");
        SELECT("status");
        SELECT("checkin_id");
        FROM("user_reserve");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        UserReserve record = (UserReserve) parameter.get("record");
        UserReserveExample example = (UserReserveExample) parameter.get("example");

        BEGIN();
        UPDATE("user_reserve");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }

        if (record.getCheckinDate() != null) {
            SET("checkin_date = #{record.checkinDate,jdbcType=DATE}");
        }

        if (record.getCheckoutDate() != null) {
            SET("checkout_date = #{record.checkoutDate,jdbcType=DATE}");
        }

        if (record.getRoomType() != null) {
            SET("room_type = #{record.roomType,jdbcType=VARCHAR}");
        }

        if (record.getTime() != null) {
            SET("time = #{record.time,jdbcType=TIMESTAMP}");
        }

        if (record.getPhoneNumber() != null) {
            SET("phone_number = #{record.phoneNumber,jdbcType=VARCHAR}");
        }

        if (record.getNum() != null) {
            SET("num = #{record.num,jdbcType=INTEGER}");
        }

        if (record.getUserId() != null) {
            SET("user_id = #{record.userId,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            SET("status = #{record.status,jdbcType=INTEGER}");
        }

        if (record.getCheckinId() != null) {
            SET("checkin_id = #{record.checkinId,jdbcType=INTEGER}");
        }
        
        if (record.getFace() != null) {
            SET("face = #{record.face,jdbcType=VARBINARY}");
        }

        applyWhere(example, true);
        return SQL();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("user_reserve");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("checkin_date = #{record.checkinDate,jdbcType=DATE}");
        SET("checkout_date = #{record.checkoutDate,jdbcType=DATE}");
        SET("room_type = #{record.roomType,jdbcType=VARCHAR}");
        SET("time = #{record.time,jdbcType=TIMESTAMP}");
        SET("phone_number = #{record.phoneNumber,jdbcType=VARCHAR}");
        SET("num = #{record.num,jdbcType=INTEGER}");
        SET("user_id = #{record.userId,jdbcType=INTEGER}");
        SET("status = #{record.status,jdbcType=INTEGER}");
        SET("checkin_id = #{record.checkinId,jdbcType=INTEGER}");
        SET("face = #{record.face,jdbcType=VARBINARY}");

        UserReserveExample example = (UserReserveExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("user_reserve");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("checkin_date = #{record.checkinDate,jdbcType=DATE}");
        SET("checkout_date = #{record.checkoutDate,jdbcType=DATE}");
        SET("room_type = #{record.roomType,jdbcType=VARCHAR}");
        SET("time = #{record.time,jdbcType=TIMESTAMP}");
        SET("phone_number = #{record.phoneNumber,jdbcType=VARCHAR}");
        SET("num = #{record.num,jdbcType=INTEGER}");
        SET("user_id = #{record.userId,jdbcType=INTEGER}");
        SET("status = #{record.status,jdbcType=INTEGER}");
        SET("checkin_id = #{record.checkinId,jdbcType=INTEGER}");
        
        UserReserveExample example = (UserReserveExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(UserReserve record) {
        BEGIN();
        UPDATE("user_reserve");

        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getCheckinDate() != null) {
            SET("checkin_date = #{checkinDate,jdbcType=DATE}");
        }

        if (record.getCheckoutDate() != null) {
            SET("checkout_date = #{checkoutDate,jdbcType=DATE}");
        }

        if (record.getRoomType() != null) {
            SET("room_type = #{roomType,jdbcType=VARCHAR}");
        }

        if (record.getTime() != null) {
            SET("time = #{time,jdbcType=TIMESTAMP}");
        }

        if (record.getPhoneNumber() != null) {
            SET("phone_number = #{phoneNumber,jdbcType=VARCHAR}");
        }

        if (record.getNum() != null) {
            SET("num = #{num,jdbcType=INTEGER}");
        }

        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=INTEGER}");
        }

        if (record.getCheckinId() != null) {
            SET("checkin_id = #{checkinId,jdbcType=INTEGER}");
        }
        
        if (record.getFace() != null) {
            SET("face = #{face,jdbcType=VARBINARY}");
        }

        WHERE("id = #{id,jdbcType=INTEGER}");

        return SQL();
    }

    protected void applyWhere(UserReserveExample example, boolean includeExamplePhrase) {
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