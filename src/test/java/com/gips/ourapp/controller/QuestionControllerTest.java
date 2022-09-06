package com.gips.ourapp.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gips.ourapp.forms.QuestionForm;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class QuestionControllerTest {

	// モック
	private MockMvc mockMvc;

	@Autowired
	private QuestionController target;

	// テストメソッド(@Testをつけたメソッド)の実行前に実施
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}

	@Test
	void testQuestions() throws Exception {
		// getでControllerを呼出
		MvcResult result = mockMvc.perform(get("/questions")).andDo(print()) // コンソールに取得結果を出力
				.andExpect(status().isOk()) // HTTPステータスのテスト
				.andExpect(view().name("questions")) // 呼出すViewのテスト
				.andReturn();
		// Controllerが設定したFormの取り出し

		QuestionForm form = (QuestionForm) result.getModelAndView().getModel().get("form");

		// Formの値のテスト (Formの値がNULLであれば成功)
		assertNotNull(form.getChoice1());
	}

	@Test
	public void testResult() {
		fail("まだ実装されていません");
	}

}
