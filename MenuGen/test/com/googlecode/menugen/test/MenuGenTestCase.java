/**
 * 
 */
package com.googlecode.menugen.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Load the MenuGen Spring Application Context
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class MenuGenTestCase {

}
