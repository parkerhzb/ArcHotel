package com.arcsoft.hotel.pojo;

import java.util.ArrayList;
import java.util.List;

public class InpersonExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InpersonExample() {
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

        public Criteria andCheckinIdIsNull() {
            addCriterion("checkin_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckinIdIsNotNull() {
            addCriterion("checkin_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinIdEqualTo(Integer value) {
            addCriterion("checkin_id =", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdNotEqualTo(Integer value) {
            addCriterion("checkin_id <>", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdGreaterThan(Integer value) {
            addCriterion("checkin_id >", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("checkin_id >=", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdLessThan(Integer value) {
            addCriterion("checkin_id <", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdLessThanOrEqualTo(Integer value) {
            addCriterion("checkin_id <=", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdIn(List<Integer> values) {
            addCriterion("checkin_id in", values, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdNotIn(List<Integer> values) {
            addCriterion("checkin_id not in", values, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdBetween(Integer value1, Integer value2) {
            addCriterion("checkin_id between", value1, value2, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdNotBetween(Integer value1, Integer value2) {
            addCriterion("checkin_id not between", value1, value2, "checkinId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeIsNull() {
            addCriterion("document_type is null");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeIsNotNull() {
            addCriterion("document_type is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeEqualTo(Integer value) {
            addCriterion("document_type =", value, "documentType");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeNotEqualTo(Integer value) {
            addCriterion("document_type <>", value, "documentType");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeGreaterThan(Integer value) {
            addCriterion("document_type >", value, "documentType");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("document_type >=", value, "documentType");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeLessThan(Integer value) {
            addCriterion("document_type <", value, "documentType");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("document_type <=", value, "documentType");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeIn(List<Integer> values) {
            addCriterion("document_type in", values, "documentType");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeNotIn(List<Integer> values) {
            addCriterion("document_type not in", values, "documentType");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeBetween(Integer value1, Integer value2) {
            addCriterion("document_type between", value1, value2, "documentType");
            return (Criteria) this;
        }

        public Criteria andDocumentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("document_type not between", value1, value2, "documentType");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberIsNull() {
            addCriterion("document_number is null");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberIsNotNull() {
            addCriterion("document_number is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberEqualTo(String value) {
            addCriterion("document_number =", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberNotEqualTo(String value) {
            addCriterion("document_number <>", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberGreaterThan(String value) {
            addCriterion("document_number >", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberGreaterThanOrEqualTo(String value) {
            addCriterion("document_number >=", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberLessThan(String value) {
            addCriterion("document_number <", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberLessThanOrEqualTo(String value) {
            addCriterion("document_number <=", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberLike(String value) {
            addCriterion("document_number like", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberNotLike(String value) {
            addCriterion("document_number not like", value, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberIn(List<String> values) {
            addCriterion("document_number in", values, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberNotIn(List<String> values) {
            addCriterion("document_number not in", values, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberBetween(String value1, String value2) {
            addCriterion("document_number between", value1, value2, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andDocumentNumberNotBetween(String value1, String value2) {
            addCriterion("document_number not between", value1, value2, "documentNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(String value) {
            addCriterion("phone_number =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(String value) {
            addCriterion("phone_number <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(String value) {
            addCriterion("phone_number >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("phone_number >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(String value) {
            addCriterion("phone_number <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("phone_number <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLike(String value) {
            addCriterion("phone_number like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotLike(String value) {
            addCriterion("phone_number not like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<String> values) {
            addCriterion("phone_number in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<String> values) {
            addCriterion("phone_number not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(String value1, String value2) {
            addCriterion("phone_number between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("phone_number not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andFaceurlIsNull() {
            addCriterion("faceurl is null");
            return (Criteria) this;
        }

        public Criteria andFaceurlIsNotNull() {
            addCriterion("faceurl is not null");
            return (Criteria) this;
        }

        public Criteria andFaceurlEqualTo(String value) {
            addCriterion("faceurl =", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotEqualTo(String value) {
            addCriterion("faceurl <>", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlGreaterThan(String value) {
            addCriterion("faceurl >", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlGreaterThanOrEqualTo(String value) {
            addCriterion("faceurl >=", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlLessThan(String value) {
            addCriterion("faceurl <", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlLessThanOrEqualTo(String value) {
            addCriterion("faceurl <=", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlLike(String value) {
            addCriterion("faceurl like", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotLike(String value) {
            addCriterion("faceurl not like", value, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlIn(List<String> values) {
            addCriterion("faceurl in", values, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotIn(List<String> values) {
            addCriterion("faceurl not in", values, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlBetween(String value1, String value2) {
            addCriterion("faceurl between", value1, value2, "faceurl");
            return (Criteria) this;
        }

        public Criteria andFaceurlNotBetween(String value1, String value2) {
            addCriterion("faceurl not between", value1, value2, "faceurl");
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