package com.hao.app.pojo;

public class SysRole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.id
     *
     * @mbggenerated Wed Jul 06 20:14:31 CST 2016
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.name
     *
     * @mbggenerated Wed Jul 06 20:14:31 CST 2016
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.intro
     *
     * @mbggenerated Wed Jul 06 20:14:31 CST 2016
     */
    private String intro;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.id
     *
     * @return the value of sys_role.id
     *
     * @mbggenerated Wed Jul 06 20:14:31 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.id
     *
     * @param id the value for sys_role.id
     *
     * @mbggenerated Wed Jul 06 20:14:31 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.name
     *
     * @return the value of sys_role.name
     *
     * @mbggenerated Wed Jul 06 20:14:31 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.name
     *
     * @param name the value for sys_role.name
     *
     * @mbggenerated Wed Jul 06 20:14:31 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.intro
     *
     * @return the value of sys_role.intro
     *
     * @mbggenerated Wed Jul 06 20:14:31 CST 2016
     */
    public String getIntro() {
        return intro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.intro
     *
     * @param intro the value for sys_role.intro
     *
     * @mbggenerated Wed Jul 06 20:14:31 CST 2016
     */
    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}