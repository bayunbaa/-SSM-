package com.xh.entity;

import java.util.ArrayList;
import java.util.List;

public class InformExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InformExample() {
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

        public Criteria andIidIsNull() {
            addCriterion("iId is null");
            return (Criteria) this;
        }

        public Criteria andIidIsNotNull() {
            addCriterion("iId is not null");
            return (Criteria) this;
        }

        public Criteria andIidEqualTo(Integer value) {
            addCriterion("iId =", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotEqualTo(Integer value) {
            addCriterion("iId <>", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidGreaterThan(Integer value) {
            addCriterion("iId >", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidGreaterThanOrEqualTo(Integer value) {
            addCriterion("iId >=", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLessThan(Integer value) {
            addCriterion("iId <", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidLessThanOrEqualTo(Integer value) {
            addCriterion("iId <=", value, "iid");
            return (Criteria) this;
        }

        public Criteria andIidIn(List<Integer> values) {
            addCriterion("iId in", values, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotIn(List<Integer> values) {
            addCriterion("iId not in", values, "iid");
            return (Criteria) this;
        }

        public Criteria andIidBetween(Integer value1, Integer value2) {
            addCriterion("iId between", value1, value2, "iid");
            return (Criteria) this;
        }

        public Criteria andIidNotBetween(Integer value1, Integer value2) {
            addCriterion("iId not between", value1, value2, "iid");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uId is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uId is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uId =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uId <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uId >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uId >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uId <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uId <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uId in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uId not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uId between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uId not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andIbodyIsNull() {
            addCriterion("ibody is null");
            return (Criteria) this;
        }

        public Criteria andIbodyIsNotNull() {
            addCriterion("ibody is not null");
            return (Criteria) this;
        }

        public Criteria andIbodyEqualTo(String value) {
            addCriterion("ibody =", value, "ibody");
            return (Criteria) this;
        }

        public Criteria andIbodyNotEqualTo(String value) {
            addCriterion("ibody <>", value, "ibody");
            return (Criteria) this;
        }

        public Criteria andIbodyGreaterThan(String value) {
            addCriterion("ibody >", value, "ibody");
            return (Criteria) this;
        }

        public Criteria andIbodyGreaterThanOrEqualTo(String value) {
            addCriterion("ibody >=", value, "ibody");
            return (Criteria) this;
        }

        public Criteria andIbodyLessThan(String value) {
            addCriterion("ibody <", value, "ibody");
            return (Criteria) this;
        }

        public Criteria andIbodyLessThanOrEqualTo(String value) {
            addCriterion("ibody <=", value, "ibody");
            return (Criteria) this;
        }

        public Criteria andIbodyLike(String value) {
            addCriterion("ibody like", value, "ibody");
            return (Criteria) this;
        }

        public Criteria andIbodyNotLike(String value) {
            addCriterion("ibody not like", value, "ibody");
            return (Criteria) this;
        }

        public Criteria andIbodyIn(List<String> values) {
            addCriterion("ibody in", values, "ibody");
            return (Criteria) this;
        }

        public Criteria andIbodyNotIn(List<String> values) {
            addCriterion("ibody not in", values, "ibody");
            return (Criteria) this;
        }

        public Criteria andIbodyBetween(String value1, String value2) {
            addCriterion("ibody between", value1, value2, "ibody");
            return (Criteria) this;
        }

        public Criteria andIbodyNotBetween(String value1, String value2) {
            addCriterion("ibody not between", value1, value2, "ibody");
            return (Criteria) this;
        }

        public Criteria andItypeIsNull() {
            addCriterion("itype is null");
            return (Criteria) this;
        }

        public Criteria andItypeIsNotNull() {
            addCriterion("itype is not null");
            return (Criteria) this;
        }

        public Criteria andItypeEqualTo(Integer value) {
            addCriterion("itype =", value, "itype");
            return (Criteria) this;
        }

        public Criteria andItypeNotEqualTo(Integer value) {
            addCriterion("itype <>", value, "itype");
            return (Criteria) this;
        }

        public Criteria andItypeGreaterThan(Integer value) {
            addCriterion("itype >", value, "itype");
            return (Criteria) this;
        }

        public Criteria andItypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("itype >=", value, "itype");
            return (Criteria) this;
        }

        public Criteria andItypeLessThan(Integer value) {
            addCriterion("itype <", value, "itype");
            return (Criteria) this;
        }

        public Criteria andItypeLessThanOrEqualTo(Integer value) {
            addCriterion("itype <=", value, "itype");
            return (Criteria) this;
        }

        public Criteria andItypeIn(List<Integer> values) {
            addCriterion("itype in", values, "itype");
            return (Criteria) this;
        }

        public Criteria andItypeNotIn(List<Integer> values) {
            addCriterion("itype not in", values, "itype");
            return (Criteria) this;
        }

        public Criteria andItypeBetween(Integer value1, Integer value2) {
            addCriterion("itype between", value1, value2, "itype");
            return (Criteria) this;
        }

        public Criteria andItypeNotBetween(Integer value1, Integer value2) {
            addCriterion("itype not between", value1, value2, "itype");
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