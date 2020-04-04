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

import com.arcsoft.hotel.pojo.MeetingReserve;
import com.arcsoft.hotel.pojo.MeetingReserveExample.Criteria;
import com.arcsoft.hotel.pojo.MeetingReserveExample.Criterion;
import com.arcsoft.hotel.pojo.MeetingReserveExample;
import java.util.List;
import java.util.Map;

public class MeetingReserveSqlProvider {

    public String countByExample(MeetingReserveExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("meeting_reserve");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(MeetingReserveExample example) {
        BEGIN();
        DELETE_FROM("meeting_reserve");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(MeetingReserve record) {
        BEGIN();
        INSERT_INTO("meeting_reserve");

        if (record.getMeetingType() != null) {
            VALUES("meeting_type", "#{meetingType,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getPhoneNumber() != null) {
            VALUES("phone_number", "#{phoneNumber,jdbcType=VARCHAR}");
        }

        if (record.getInviteCode() != null) {
            VALUES("invite_code", "#{inviteCode,jdbcType=VARCHAR}");
        }

        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=INTEGER}");
        }

        if (record.getMeetingId() != null) {
            VALUES("meeting_id", "#{meetingId,jdbcType=INTEGER}");
        }

        if (record.getFace() != null) {
            VALUES("face", "#{face,jdbcType=VARBINARY}");
        }

        return SQL();
    }

    public String selectByExampleWithBLOBs(MeetingReserveExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("meeting_type");
        SELECT("name");
        SELECT("phone_number");
        SELECT("invite_code");
        SELECT("user_id");
        SELECT("status");
        SELECT("meeting_id");
        SELECT("face");
        FROM("meeting_reserve");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String selectByExample(MeetingReserveExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("meeting_type");
        SELECT("name");
        SELECT("phone_number");
        SELECT("invite_code");
        SELECT("user_id");
        SELECT("status");
        SELECT("meeting_id");
        FROM("meeting_reserve");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        MeetingReserve record = (MeetingReserve) parameter.get("record");
        MeetingReserveExample example = (MeetingReserveExample) parameter.get("example");

        BEGIN();
        UPDATE("meeting_reserve");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getMeetingType() != null) {
            SET("meeting_type = #{record.meetingType,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }

        if (record.getPhoneNumber() != null) {
            SET("phone_number = #{record.phoneNumber,jdbcType=VARCHAR}");
        }

        if (record.getInviteCode() != null) {
            SET("invite_code = #{record.inviteCode,jdbcType=VARCHAR}");
        }

        if (record.getUserId() != null) {
            SET("user_id = #{record.userId,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            SET("status = #{record.status,jdbcType=INTEGER}");
        }

        if (record.getMeetingId() != null) {
            SET("meeting_id = #{record.meetingId,jdbcType=INTEGER}");
        }

        if (record.getFace() != null) {
            SET("face = #{record.face,jdbcType=VARBINARY}");
        }

        applyWhere(example, true);
        return SQL();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("meeting_reserve");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("meeting_type = #{record.meetingType,jdbcType=VARCHAR}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("phone_number = #{record.phoneNumber,jdbcType=VARCHAR}");
        SET("invite_code = #{record.inviteCode,jdbcType=VARCHAR}");
        SET("user_id = #{record.userId,jdbcType=INTEGER}");
        SET("status = #{record.status,jdbcType=INTEGER}");
        SET("meeting_id = #{record.meetingId,jdbcType=INTEGER}");
        SET("face = #{record.face,jdbcType=VARBINARY}");

        MeetingReserveExample example = (MeetingReserveExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("meeting_reserve");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("meeting_type = #{record.meetingType,jdbcType=VARCHAR}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("phone_number = #{record.phoneNumber,jdbcType=VARCHAR}");
        SET("invite_code = #{record.inviteCode,jdbcType=VARCHAR}");
        SET("user_id = #{record.userId,jdbcType=INTEGER}");
        SET("status = #{record.status,jdbcType=INTEGER}");
        SET("meeting_id = #{record.meetingId,jdbcType=INTEGER}");
        
        MeetingReserveExample example = (MeetingReserveExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(MeetingReserve record) {
        BEGIN();
        UPDATE("meeting_reserve");

        if (record.getMeetingType() != null) {
            SET("meeting_type = #{meetingType,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getPhoneNumber() != null) {
            SET("phone_number = #{phoneNumber,jdbcType=VARCHAR}");
        }

        if (record.getInviteCode() != null) {
            SET("invite_code = #{inviteCode,jdbcType=VARCHAR}");
        }

        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=INTEGER}");
        }

        if (record.getMeetingId() != null) {
            SET("meeting_id = #{meetingId,jdbcType=INTEGER}");
        }

        if (record.getFace() != null) {
            SET("face = #{face,jdbcType=VARBINARY}");
        }

        WHERE("id = #{id,jdbcType=INTEGER}");

        return SQL();
    }

    protected void applyWhere(MeetingReserveExample example, boolean includeExamplePhrase) {
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