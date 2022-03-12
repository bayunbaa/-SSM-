package com.xh.entity;

import java.util.ArrayList;
import java.util.List;

public class FacilityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FacilityExample() {
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

        public Criteria andFtidIsNull() {
            addCriterion("ftid is null");
            return (Criteria) this;
        }

        public Criteria andFtidIsNotNull() {
            addCriterion("ftid is not null");
            return (Criteria) this;
        }

        public Criteria andFtidEqualTo(Integer value) {
            addCriterion("ftid =", value, "ftid");
            return (Criteria) this;
        }

        public Criteria andFtidNotEqualTo(Integer value) {
            addCriterion("ftid <>", value, "ftid");
            return (Criteria) this;
        }

        public Criteria andFtidGreaterThan(Integer value) {
            addCriterion("ftid >", value, "ftid");
            return (Criteria) this;
        }

        public Criteria andFtidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ftid >=", value, "ftid");
            return (Criteria) this;
        }

        public Criteria andFtidLessThan(Integer value) {
            addCriterion("ftid <", value, "ftid");
            return (Criteria) this;
        }

        public Criteria andFtidLessThanOrEqualTo(Integer value) {
            addCriterion("ftid <=", value, "ftid");
            return (Criteria) this;
        }

        public Criteria andFtidIn(List<Integer> values) {
            addCriterion("ftid in", values, "ftid");
            return (Criteria) this;
        }

        public Criteria andFtidNotIn(List<Integer> values) {
            addCriterion("ftid not in", values, "ftid");
            return (Criteria) this;
        }

        public Criteria andFtidBetween(Integer value1, Integer value2) {
            addCriterion("ftid between", value1, value2, "ftid");
            return (Criteria) this;
        }

        public Criteria andFtidNotBetween(Integer value1, Integer value2) {
            addCriterion("ftid not between", value1, value2, "ftid");
            return (Criteria) this;
        }

        public Criteria andFnameIsNull() {
            addCriterion("fname is null");
            return (Criteria) this;
        }

        public Criteria andFnameIsNotNull() {
            addCriterion("fname is not null");
            return (Criteria) this;
        }

        public Criteria andFnameEqualTo(String value) {
            addCriterion("fname =", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotEqualTo(String value) {
            addCriterion("fname <>", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThan(String value) {
            addCriterion("fname >", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThanOrEqualTo(String value) {
            addCriterion("fname >=", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLessThan(String value) {
            addCriterion("fname <", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLessThanOrEqualTo(String value) {
            addCriterion("fname <=", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLike(String value) {
            addCriterion("fname like", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotLike(String value) {
            addCriterion("fname not like", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameIn(List<String> values) {
            addCriterion("fname in", values, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotIn(List<String> values) {
            addCriterion("fname not in", values, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameBetween(String value1, String value2) {
            addCriterion("fname between", value1, value2, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotBetween(String value1, String value2) {
            addCriterion("fname not between", value1, value2, "fname");
            return (Criteria) this;
        }

        public Criteria andFtimeIsNull() {
            addCriterion("ftime is null");
            return (Criteria) this;
        }

        public Criteria andFtimeIsNotNull() {
            addCriterion("ftime is not null");
            return (Criteria) this;
        }

        public Criteria andFtimeEqualTo(String value) {
            addCriterion("ftime =", value, "ftime");
            return (Criteria) this;
        }

        public Criteria andFtimeNotEqualTo(String value) {
            addCriterion("ftime <>", value, "ftime");
            return (Criteria) this;
        }

        public Criteria andFtimeGreaterThan(String value) {
            addCriterion("ftime >", value, "ftime");
            return (Criteria) this;
        }

        public Criteria andFtimeGreaterThanOrEqualTo(String value) {
            addCriterion("ftime >=", value, "ftime");
            return (Criteria) this;
        }

        public Criteria andFtimeLessThan(String value) {
            addCriterion("ftime <", value, "ftime");
            return (Criteria) this;
        }

        public Criteria andFtimeLessThanOrEqualTo(String value) {
            addCriterion("ftime <=", value, "ftime");
            return (Criteria) this;
        }

        public Criteria andFtimeLike(String value) {
            addCriterion("ftime like", value, "ftime");
            return (Criteria) this;
        }

        public Criteria andFtimeNotLike(String value) {
            addCriterion("ftime not like", value, "ftime");
            return (Criteria) this;
        }

        public Criteria andFtimeIn(List<String> values) {
            addCriterion("ftime in", values, "ftime");
            return (Criteria) this;
        }

        public Criteria andFtimeNotIn(List<String> values) {
            addCriterion("ftime not in", values, "ftime");
            return (Criteria) this;
        }

        public Criteria andFtimeBetween(String value1, String value2) {
            addCriterion("ftime between", value1, value2, "ftime");
            return (Criteria) this;
        }

        public Criteria andFtimeNotBetween(String value1, String value2) {
            addCriterion("ftime not between", value1, value2, "ftime");
            return (Criteria) this;
        }

        public Criteria andFnumIsNull() {
            addCriterion("fnum is null");
            return (Criteria) this;
        }

        public Criteria andFnumIsNotNull() {
            addCriterion("fnum is not null");
            return (Criteria) this;
        }

        public Criteria andFnumEqualTo(Integer value) {
            addCriterion("fnum =", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumNotEqualTo(Integer value) {
            addCriterion("fnum <>", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumGreaterThan(Integer value) {
            addCriterion("fnum >", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("fnum >=", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumLessThan(Integer value) {
            addCriterion("fnum <", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumLessThanOrEqualTo(Integer value) {
            addCriterion("fnum <=", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumIn(List<Integer> values) {
            addCriterion("fnum in", values, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumNotIn(List<Integer> values) {
            addCriterion("fnum not in", values, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumBetween(Integer value1, Integer value2) {
            addCriterion("fnum between", value1, value2, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumNotBetween(Integer value1, Integer value2) {
            addCriterion("fnum not between", value1, value2, "fnum");
            return (Criteria) this;
        }

        public Criteria andFtypeIsNull() {
            addCriterion("ftype is null");
            return (Criteria) this;
        }

        public Criteria andFtypeIsNotNull() {
            addCriterion("ftype is not null");
            return (Criteria) this;
        }

        public Criteria andFtypeEqualTo(Integer value) {
            addCriterion("ftype =", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotEqualTo(Integer value) {
            addCriterion("ftype <>", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThan(Integer value) {
            addCriterion("ftype >", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ftype >=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThan(Integer value) {
            addCriterion("ftype <", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThanOrEqualTo(Integer value) {
            addCriterion("ftype <=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeIn(List<Integer> values) {
            addCriterion("ftype in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotIn(List<Integer> values) {
            addCriterion("ftype not in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeBetween(Integer value1, Integer value2) {
            addCriterion("ftype between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ftype not between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFfctoryIsNull() {
            addCriterion("ffctory is null");
            return (Criteria) this;
        }

        public Criteria andFfctoryIsNotNull() {
            addCriterion("ffctory is not null");
            return (Criteria) this;
        }

        public Criteria andFfctoryEqualTo(String value) {
            addCriterion("ffctory =", value, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFfctoryNotEqualTo(String value) {
            addCriterion("ffctory <>", value, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFfctoryGreaterThan(String value) {
            addCriterion("ffctory >", value, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFfctoryGreaterThanOrEqualTo(String value) {
            addCriterion("ffctory >=", value, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFfctoryLessThan(String value) {
            addCriterion("ffctory <", value, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFfctoryLessThanOrEqualTo(String value) {
            addCriterion("ffctory <=", value, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFfctoryLike(String value) {
            addCriterion("ffctory like", value, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFfctoryNotLike(String value) {
            addCriterion("ffctory not like", value, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFfctoryIn(List<String> values) {
            addCriterion("ffctory in", values, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFfctoryNotIn(List<String> values) {
            addCriterion("ffctory not in", values, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFfctoryBetween(String value1, String value2) {
            addCriterion("ffctory between", value1, value2, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFfctoryNotBetween(String value1, String value2) {
            addCriterion("ffctory not between", value1, value2, "ffctory");
            return (Criteria) this;
        }

        public Criteria andFtrangeIsNull() {
            addCriterion("ftrange is null");
            return (Criteria) this;
        }

        public Criteria andFtrangeIsNotNull() {
            addCriterion("ftrange is not null");
            return (Criteria) this;
        }

        public Criteria andFtrangeEqualTo(String value) {
            addCriterion("ftrange =", value, "ftrange");
            return (Criteria) this;
        }

        public Criteria andFtrangeNotEqualTo(String value) {
            addCriterion("ftrange <>", value, "ftrange");
            return (Criteria) this;
        }

        public Criteria andFtrangeGreaterThan(String value) {
            addCriterion("ftrange >", value, "ftrange");
            return (Criteria) this;
        }

        public Criteria andFtrangeGreaterThanOrEqualTo(String value) {
            addCriterion("ftrange >=", value, "ftrange");
            return (Criteria) this;
        }

        public Criteria andFtrangeLessThan(String value) {
            addCriterion("ftrange <", value, "ftrange");
            return (Criteria) this;
        }

        public Criteria andFtrangeLessThanOrEqualTo(String value) {
            addCriterion("ftrange <=", value, "ftrange");
            return (Criteria) this;
        }

        public Criteria andFtrangeLike(String value) {
            addCriterion("ftrange like", value, "ftrange");
            return (Criteria) this;
        }

        public Criteria andFtrangeNotLike(String value) {
            addCriterion("ftrange not like", value, "ftrange");
            return (Criteria) this;
        }

        public Criteria andFtrangeIn(List<String> values) {
            addCriterion("ftrange in", values, "ftrange");
            return (Criteria) this;
        }

        public Criteria andFtrangeNotIn(List<String> values) {
            addCriterion("ftrange not in", values, "ftrange");
            return (Criteria) this;
        }

        public Criteria andFtrangeBetween(String value1, String value2) {
            addCriterion("ftrange between", value1, value2, "ftrange");
            return (Criteria) this;
        }

        public Criteria andFtrangeNotBetween(String value1, String value2) {
            addCriterion("ftrange not between", value1, value2, "ftrange");
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