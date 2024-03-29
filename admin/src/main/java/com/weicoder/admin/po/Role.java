package com.weicoder.admin.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.weicoder.frame.entity.base.BaseEntityId;

/**
 * 角色实体
 * @author WD
 * @since JDK7
 * @version 1.0 2009-11-23
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntityId {
	// 名称
	private String			name;
	// 操作列表
	@ManyToMany
	@JoinTable(name = "role_operate", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "operate"))
	private List<Operate>	operates;
	// 菜单
	@ManyToMany
	@JoinTable(name = "role_menu", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
	private List<Menu>		menus;
}