package com.xh.entity;

import java.util.ArrayList;
import java.util.List;

public class DeliveryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeliveryExample() {
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

        public Criteria andDtidIsNull() {
            addCriterion("dtid is null");
            return (Criteria) this;
        }

        public Criteria andDtidIsNotNull() {
            addCriterion("dtid is not null");
            return (Criteria) this;
        }

        public Criteria andDtidEqualTo(Integer value) {
            addCriterion("dtid =", value, "dtid");
            return (Criteria) this;
        }

        public Criteria andDtidNotEqualTo(Integer value) {
            addCriterion("dtid <>", value, "dtid");
            return (Criteria) this;
        }

        public Criteria andDtidGreaterThan(Integer value) {
            addCriterion("dtid >", value, "dtid");
            return (Criteria) this;
        }

        public Criteria andDtidGreaterThanOrEqualTo(Integer value) {
            addCriterion("dtid >=", value, "dtid");
            return (Criteria) this;
        }

        public Criteria andDtidLessThan(Integer value) {
            addCriterion("dtid <", value, "dtid");
            return (Criteria) this;
        }

        public Criteria andDtidLessThanOrEqualTo(Integer value) {
            addCriterion("dtid <=", value, "dtid");
            return (Criteria) this;
        }

        public Criteria andDtidIn(List<Integer> values) {
            addCriterion("dtid in", values, "dtid");
            return (Criteria) this;
        }

        public Criteria andDtidNotIn(List<Integer> values) {
            addCriterion("dtid not in", values, "dtid");
            return (Criteria) this;
        }

        public Criteria andDtidBetween(Integer value1, Integer value2) {
            addCriterion("dtid between", value1, value2, "dtid");
            return (Criteria) this;
        }

        public Criteria andDtidNotBetween(Integer value1, Integer value2) {
            addCriterion("dtid not between", value1, value2, "dtid");
            return (Criteria) this;
        }

        public Criteria andDnameIsNull() {
            addCriterion("dname is null");
            return (Criteria) this;
        }

        public Criteria andDnameIsNotNull() {
            addCriterion("dname is not null");
            return (Criteria) this;
        }

        public Criteria andDnameEqualTo(String value) {
            addCriterion("dname =", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotEqualTo(String value) {
            addCriterion("dname <>", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameGreaterThan(String value) {
            addCriterion("dname >", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameGreaterThanOrEqualTo(String value) {
            addCriterion("dname >=", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameLessThan(String value) {
            addCriterion("dname <", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameLessThanOrEqualTo(String value) {
            addCriterion("dname <=", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameLike(String value) {
            addCriterion("dname like", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotLike(String value) {
            addCriterion("dname not like", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameIn(List<String> values) {
            addCriterion("dname in", values, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotIn(List<String> values) {
            addCriterion("dname not in", values, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameBetween(String value1, String value2) {
            addCriterion("dname between", value1, value2, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotBetween(String value1, String value2) {
            addCriterion("dname not between", value1, value2, "dname");
            return (Criteria) this;
        }

        public Criteria andDtimeIsNull() {
            addCriterion("dtime is null");
            return (Criteria) this;
        }

        public Criteria andDtimeIsNotNull() {
            addCriterion("dtime is not null");
            return (Criteria) this;
        }

        public Criteria andDtimeEqualTo(String value) {
            addCriterion("dtime =", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeNotEqualTo(String value) {
            addCriterion("dtime <>", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeGreaterThan(String value) {
            addCriterion("dtime >", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeGreaterThanOrEqualTo(String value) {
            addCriterion("dtime >=", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeLessThan(String value) {
            addCriterion("dtime <", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeLessThanOrEqualTo(String value) {
            addCriterion("dtime <=", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeLike(String value) {
            addCriterion("dtime like", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeNotLike(String value) {
            addCriterion("dtime not like", value, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeIn(List<String> values) {
            addCriterion("dtime in", values, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeNotIn(List<String> values) {
            addCriterion("dtime not in", values, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeBetween(String value1, String value2) {
            addCriterion("dtime between", value1, value2, "dtime");
            return (Criteria) this;
        }

        public Criteria andDtimeNotBetween(String value1, String value2) {
            addCriterion("dtime not between", value1, value2, "dtime");
            return (Criteria) this;
        }

        public Criteria andDnumIsNull() {
            addCriterion("dnum is null");
            return (Criteria) this;
        }

        public Criteria andDnumIsNotNull() {
            addCriterion("dnum is not null");
            return (Criteria) this;
        }

        public Criteria andDnumEqualTo(Integer value) {
            addCriterion("dnum =", value, "dnum");
            return (Criteria) this;
        }

        public Criteria andDnumNotEqualTo(Integer value) {
            addCriterion("dnum <>", value, "dnum");
            return (Criteria) this;
        }

        public Criteria andDnumGreaterThan(Integer value) {
            addCriterion("dnum >", value, "dnum");
            return (Criteria) this;
        }

        public Criteria andDnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("dnum >=", value, "dnum");
            return (Criteria) this;
        }

        public Criteria andDnumLessThan(Integer value) {
            addCriterion("dnum <", value, "dnum");
            return (Criteria) this;
        }

        public Criteria andDnumLessThanOrEqualTo(Integer value) {
            addCriterion("dnum <=", value, "dnum");
            return (Criteria) this;
        }

        public Criteria andDnumIn(List<Integer> values) {
            addCriterion("dnum in", values, "dnum");
            return (Criteria) this;
        }

        public Criteria andDnumNotIn(List<Integer> values) {
            addCriterion("dnum not in", values, "dnum");
            return (Criteria) this;
        }

        public Criteria andDnumBetween(Integer value1, Integer value2) {
            addCriterion("dnum between", value1, value2, "dnum");
            return (Criteria) this;
        }

        public Criteria andDnumNotBetween(Integer value1, Integer value2) {
            addCriterion("dnum not between", value1, value2, "dnum");
            return (Criteria) this;
        }

        public Criteria andDtypeIsNull() {
            addCriterion("dtype is null");
            return (Criteria) this;
        }

        public Criteria andDtypeIsNotNull() {
            addCriterion("dtype is not null");
            return (Criteria) this;
        }

        public Criteria andDtypeEqualTo(Integer value) {
            addCriterion("dtype =", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeNotEqualTo(Integer value) {
            addCriterion("dtype <>", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeGreaterThan(Integer value) {
            addCriterion("dtype >", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dtype >=", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeLessThan(Integer value) {
            addCriterion("dtype <", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeLessThanOrEqualTo(Integer value) {
            addCriterion("dtype <=", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeIn(List<Integer> values) {
            addCriterion("dtype in", values, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeNotIn(List<Integer> values) {
            addCriterion("dtype not in", values, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeBetween(Integer value1, Integer value2) {
            addCriterion("dtype between", value1, value2, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dtype not between", value1, value2, "dtype");
            return (Criteria) this;
        }

        public Criteria andDfctoryIsNull() {
            addCriterion("dfctory is null");
            return (Criteria) this;
        }

        public Criteria andDfctoryIsNotNull() {
            addCriterion("dfctory is not null");
            return (Criteria) this;
        }

        public Criteria andDfctoryEqualTo(String value) {
            addCriterion("dfctory =", value, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDfctoryNotEqualTo(String value) {
            addCriterion("dfctory <>", value, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDfctoryGreaterThan(String value) {
            addCriterion("dfctory >", value, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDfctoryGreaterThanOrEqualTo(String value) {
            addCriterion("dfctory >=", value, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDfctoryLessThan(String value) {
            addCriterion("dfctory <", value, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDfctoryLessThanOrEqualTo(String value) {
            addCriterion("dfctory <=", value, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDfctoryLike(String value) {
            addCriterion("dfctory like", value, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDfctoryNotLike(String value) {
            addCriterion("dfctory not like", value, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDfctoryIn(List<String> values) {
            addCriterion("dfctory in", values, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDfctoryNotIn(List<String> values) {
            addCriterion("dfctory not in", values, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDfctoryBetween(String value1, String value2) {
            addCriterion("dfctory between", value1, value2, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDfctoryNotBetween(String value1, String value2) {
            addCriterion("dfctory not between", value1, value2, "dfctory");
            return (Criteria) this;
        }

        public Criteria andDtrangeIsNull() {
            addCriterion("dtrange is null");
            return (Criteria) this;
        }

        public Criteria andDtrangeIsNotNull() {
            addCriterion("dtrange is not null");
            return (Criteria) this;
        }

        public Criteria andDtrangeEqualTo(String value) {
            addCriterion("dtrange =", value, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDtrangeNotEqualTo(String value) {
            addCriterion("dtrange <>", value, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDtrangeGreaterThan(String value) {
            addCriterion("dtrange >", value, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDtrangeGreaterThanOrEqualTo(String value) {
            addCriterion("dtrange >=", value, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDtrangeLessThan(String value) {
            addCriterion("dtrange <", value, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDtrangeLessThanOrEqualTo(String value) {
            addCriterion("dtrange <=", value, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDtrangeLike(String value) {
            addCriterion("dtrange like", value, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDtrangeNotLike(String value) {
            addCriterion("dtrange not like", value, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDtrangeIn(List<String> values) {
            addCriterion("dtrange in", values, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDtrangeNotIn(List<String> values) {
            addCriterion("dtrange not in", values, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDtrangeBetween(String value1, String value2) {
            addCriterion("dtrange between", value1, value2, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDtrangeNotBetween(String value1, String value2) {
            addCriterion("dtrange not between", value1, value2, "dtrange");
            return (Criteria) this;
        }

        public Criteria andDcollegeIsNull() {
            addCriterion("dcollege is null");
            return (Criteria) this;
        }

        public Criteria andDcollegeIsNotNull() {
            addCriterion("dcollege is not null");
            return (Criteria) this;
        }

        public Criteria andDcollegeEqualTo(Integer value) {
            addCriterion("dcollege =", value, "dcollege");
            return (Criteria) this;
        }

        public Criteria andDcollegeNotEqualTo(Integer value) {
            addCriterion("dcollege <>", value, "dcollege");
            return (Criteria) this;
        }

        public Criteria andDcollegeGreaterThan(Integer value) {
            addCriterion("dcollege >", value, "dcollege");
            return (Criteria) this;
        }

        public Criteria andDcollegeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dcollege >=", value, "dcollege");
            return (Criteria) this;
        }

        public Criteria andDcollegeLessThan(Integer value) {
            addCriterion("dcollege <", value, "dcollege");
            return (Criteria) this;
        }

        public Criteria andDcollegeLessThanOrEqualTo(Integer value) {
            addCriterion("dcollege <=", value, "dcollege");
            return (Criteria) this;
        }

        public Criteria andDcollegeIn(List<Integer> values) {
            addCriterion("dcollege in", values, "dcollege");
            return (Criteria) this;
        }

        public Criteria andDcollegeNotIn(List<Integer> values) {
            addCriterion("dcollege not in", values, "dcollege");
            return (Criteria) this;
        }

        public Criteria andDcollegeBetween(Integer value1, Integer value2) {
            addCriterion("dcollege between", value1, value2, "dcollege");
            return (Criteria) this;
        }

        public Criteria andDcollegeNotBetween(Integer value1, Integer value2) {
            addCriterion("dcollege not between", value1, value2, "dcollege");
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