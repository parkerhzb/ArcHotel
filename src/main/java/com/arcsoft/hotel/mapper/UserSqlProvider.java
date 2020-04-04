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

import com.arcsoft.hotel.pojo.User;
import com.arcsoft.hotel.pojo.UserExample.Criteria;
import com.arcsoft.hotel.pojo.UserExample.Criterion;
import com.arcsoft.hotel.pojo.UserExample;
import java.util.List;
import java.util.Map;

public class UserSqlProvider {

    public String countByExample(UserExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("user");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(UserExample example) {
        BEGIN();
        DELETE_FROM("user");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(User record) {
        BEGIN();
        INSERT_INTO("user");

        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=VARCHAR}");
        }

        if (record.getOpenId() != null) {
            VALUES("open_id", "#{openId,jdbcType=VARCHAR}");
        }

        if (record.getSsesId() != null) {
            VALUES("sses_id", "#{ssesId,jdbcType=VARCHAR}");
        }

        if (record.getNickname() != null) {
            VALUES("nickname", "#{nickname,jdbcType=VARCHAR}");
        }

        if (record.getHeadimgurl() != null) {
            VALUES("headimgurl", "#{headimgurl,jdbcType=VARCHAR}");
        }

        if (record.getPhoneNumber() != null) {
            VALUES("phone_number", "#{phoneNumber,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(UserExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("status");
        SELECT("open_id");
        SELECT("sses_id");
        SELECT("nickname");
        SELECT("headimgurl");
        SELECT("phone_number");
        FROM("user");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        User record = (User) parameter.get("record");
        UserExample example = (UserExample) parameter.get("example");

        BEGIN();
        UPDATE("user");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getStatus() != null) {
            SET("status = #{record.status,jdbcType=VARCHAR}");
        }

        if (record.getOpenId() != null) {
            SET("open_id = #{record.openId,jdbcType=VARCHAR}");
        }

        if (record.getSsesId() != null) {
            SET("sses_id = #{record.ssesId,jdbcType=VARCHAR}");
        }

        if (record.getNickname() != null) {
            SET("nickname = #{record.nickname,jdbcType=VARCHAR}");
        }

        if (record.getHeadimgurl() != null) {
            SET("headimgurl = #{record.headimgurl,jdbcType=VARCHAR}");
        }

        if (record.getPhoneNumber() != null) {
            SET("phone_number = #{record.phoneNumber,jdbcType=VARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("user");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("status = #{record.status,jdbcType=VARCHAR}");
        SET("open_id = #{record.openId,jdbcType=VARCHAR}");
        SET("sses_id = #{record.ssesId,jdbcType=VARCHAR}");
        SET("nickname = #{record.nickname,jdbcType=VARCHAR}");
        SET("headimgurl = #{record.headimgurl,jdbcType=VARCHAR}");
        SET("phone_number = #{record.phoneNumber,jdbcType=VARCHAR}");
        
        UserExample example = (UserExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(User record) {
        BEGIN();
        UPDATE("user");

        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=VARCHAR}");
        }

        if (record.getOpenId() != null) {
            SET("open_id = #{openId,jdbcType=VARCHAR}");
        }

        if (record.getSsesId() != null) {
            SET("sses_id = #{ssesId,jdbcType=VARCHAR}");
        }

        if (record.getNickname() != null) {
            SET("nickname = #{nickname,jdbcType=VARCHAR}");
        }

        if (record.getHeadimgurl() != null) {
            SET("headimgurl = #{headimgurl,jdbcType=VARCHAR}");
        }

        if (record.getPhoneNumber() != null) {
            SET("phone_number = #{phoneNumber,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");

        return SQL();
    }

    protected void applyWhere(UserExample example, boolean includeExamplePhrase) {
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