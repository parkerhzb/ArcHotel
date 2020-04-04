package com.arcsoft.hotel.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CheckInExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CheckInExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCheckinDateIsNull() {
            addCriterion("checkin_date is null");
            return (Criteria) this;
        }

        public Criteria andCheckinDateIsNotNull() {
            addCriterion("checkin_date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinDateEqualTo(Date value) {
            addCriterionForJDBCDate("checkin_date =", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("checkin_date <>", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateGreaterThan(Date value) {
            addCriterionForJDBCDate("checkin_date >", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("checkin_date >=", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateLessThan(Date value) {
            addCriterionForJDBCDate("checkin_date <", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("checkin_date <=", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateIn(List<Date> values) {
            addCriterionForJDBCDate("checkin_date in", values, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("checkin_date not in", values, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("checkin_date between", value1, value2, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("checkin_date not between", value1, value2, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateIsNull() {
            addCriterion("checkout_date is null");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateIsNotNull() {
            addCriterion("checkout_date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateEqualTo(Date value) {
            addCriterionForJDBCDate("checkout_date =", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("checkout_date <>", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateGreaterThan(Date value) {
            addCriterionForJDBCDate("checkout_date >", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("checkout_date >=", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateLessThan(Date value) {
            addCriterionForJDBCDate("checkout_date <", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("checkout_date <=", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateIn(List<Date> values) {
            addCriterionForJDBCDate("checkout_date in", values, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("checkout_date not in", values, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("checkout_date between", value1, value2, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("checkout_date not between", value1, value2, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(String value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(String value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(String value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(String value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(String value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(String value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLike(String value) {
            addCriterion("time like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotLike(String value) {
            addCriterion("time not like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<String> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<String> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(String value1, String value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(String value1, String value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomIdEqualTo(Integer value) {
            addCriterion("room_id =", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotEqualTo(Integer value) {
            addCriterion("room_id <>", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThan(Integer value) {
            addCriterion("room_id >", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_id >=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThan(Integer value) {
            addCriterion("room_id <", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThanOrEqualTo(Integer value) {
            addCriterion("room_id <=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIn(List<Integer> values) {
            addCriterion("room_id in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotIn(List<Integer> values) {
            addCriterion("room_id not in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdBetween(Integer value1, Integer value2) {
            addCriterion("room_id between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotBetween(Integer value1, Integer value2) {
            addCriterion("room_id not between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomIsNull() {
            addCriterion("related_room is null");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomIsNotNull() {
            addCriterion("related_room is not null");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomEqualTo(String value) {
            addCriterion("related_room =", value, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomNotEqualTo(String value) {
            addCriterion("related_room <>", value, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomGreaterThan(String value) {
            addCriterion("related_room >", value, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomGreaterThanOrEqualTo(String value) {
            addCriterion("related_room >=", value, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomLessThan(String value) {
            addCriterion("related_room <", value, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomLessThanOrEqualTo(String value) {
            addCriterion("related_room <=", value, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomLike(String value) {
            addCriterion("related_room like", value, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomNotLike(String value) {
            addCriterion("related_room not like", value, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomIn(List<String> values) {
            addCriterion("related_room in", values, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomNotIn(List<String> values) {
            addCriterion("related_room not in", values, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomBetween(String value1, String value2) {
            addCriterion("related_room between", value1, value2, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andRelatedRoomNotBetween(String value1, String value2) {
            addCriterion("related_room not between", value1, value2, "relatedRoom");
            return (Criteria) this;
        }

        public Criteria andPersonNumIsNull() {
            addCriterion("person_num is null");
            return (Criteria) this;
        }

        public Criteria andPersonNumIsNotNull() {
            addCriterion("person_num is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNumEqualTo(Integer value) {
            addCriterion("person_num =", value, "personNum");
            return (Criteria) this;
        }

        public Criteria andPersonNumNotEqualTo(Integer value) {
            addCriterion("person_num <>", value, "personNum");
            return (Criteria) this;
        }

        public Criteria andPersonNumGreaterThan(Integer value) {
            addCriterion("person_num >", value, "personNum");
            return (Criteria) this;
        }

        public Criteria andPersonNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("person_num >=", value, "personNum");
            return (Criteria) this;
        }

        public Criteria andPersonNumLessThan(Integer value) {
            addCriterion("person_num <", value, "personNum");
            return (Criteria) this;
        }

        public Criteria andPersonNumLessThanOrEqualTo(Integer value) {
            addCriterion("person_num <=", value, "personNum");
            return (Criteria) this;
        }

        public Criteria andPersonNumIn(List<Integer> values) {
            addCriterion("person_num in", values, "personNum");
            return (Criteria) this;
        }

        public Criteria andPersonNumNotIn(List<Integer> values) {
            addCriterion("person_num not in", values, "personNum");
            return (Criteria) this;
        }

        public Criteria andPersonNumBetween(Integer value1, Integer value2) {
            addCriterion("person_num between", value1, value2, "personNum");
            return (Criteria) this;
        }

        public Criteria andPersonNumNotBetween(Integer value1, Integer value2) {
            addCriterion("person_num not between", value1, value2, "personNum");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutIsNull() {
            addCriterion("is_check_out is null");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutIsNotNull() {
            addCriterion("is_check_out is not null");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutEqualTo(Byte value) {
            addCriterion("is_check_out =", value, "isCheckOut");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutNotEqualTo(Byte value) {
            addCriterion("is_check_out <>", value, "isCheckOut");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutGreaterThan(Byte value) {
            addCriterion("is_check_out >", value, "isCheckOut");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_check_out >=", value, "isCheckOut");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutLessThan(Byte value) {
            addCriterion("is_check_out <", value, "isCheckOut");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutLessThanOrEqualTo(Byte value) {
            addCriterion("is_check_out <=", value, "isCheckOut");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutIn(List<Byte> values) {
            addCriterion("is_check_out in", values, "isCheckOut");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutNotIn(List<Byte> values) {
            addCriterion("is_check_out not in", values, "isCheckOut");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutBetween(Byte value1, Byte value2) {
            addCriterion("is_check_out between", value1, value2, "isCheckOut");
            return (Criteria) this;
        }

        public Criteria andIsCheckOutNotBetween(Byte value1, Byte value2) {
            addCriterion("is_check_out not between", value1, value2, "isCheckOut");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}