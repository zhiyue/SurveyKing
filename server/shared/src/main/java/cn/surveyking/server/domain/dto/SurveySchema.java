package cn.surveyking.server.domain.dto;

import lombok.Data;

import java.util.EnumSet;
import java.util.List;

/**
 * @author javahuang
 * @date 2021/8/10
 */
@Data
public class SurveySchema implements Cloneable {

	private String id;

	private String title;

	private String description;

	private QuestionType type;

	private Attribute attribute;

	private List<DataSource> dataSource;

	private List<SurveySchema> children;

	private List<Row> row;

	/**
	 * 当前 schema 标签
	 */
	private List<String> tags;

	public enum QuestionType {

		FillBlank, Textarea, MultipleBlank, Signature, Score, Radio, Checkbox, Select, Cascader, Upload, MatrixAuto, MatrixRadio, MatrixCheckbox, MatrixFillBlank, MatrixScore, MatrixNps, Survey, QuestionSet, Pagination, Remark, SplitLine, Option, User, Dept, Nps, HorzBlank, Address;

		// 分为数据类型和空类型
		public static EnumSet<QuestionType> dataType() {
			return EnumSet.of(FillBlank, Textarea, MultipleBlank, Signature, Score, Radio, Checkbox, Select, Cascader,
					Upload, MatrixAuto, MatrixRadio, MatrixCheckbox, MatrixFillBlank, MatrixScore, MatrixNps, User,
					Dept, Nps, HorzBlank, Address);
		}

		public static EnumSet<QuestionType> voidType() {
			return EnumSet.of(Survey, QuestionSet, Pagination, Remark, SplitLine, Option);
		}

		// 考试自动计分支持题型
		public static EnumSet<QuestionType> examType() {
			return EnumSet.of(FillBlank, Textarea, MultipleBlank, Radio, Checkbox, Select, HorzBlank);
		}

	}

	@Data
	public static class Row {

		private String id;

		private String title;

	}

	@Data
	public static class Attribute {

		/**
		 * none/visible/hidden
		 */
		private String display;

		private Boolean hidden;

		private Integer width;

		private String dataType;

		private Boolean required;

		private Boolean defaultChecked;

		private Integer rows;

		/**
		 * 范围强制校验
		 */
		private String scope;

		/**
		 * 范围强校验提示信息
		 */
		private String scopeDesc;

		/**
		 * 范围软校验，超出范围会警告，但是依然可以提交
		 */
		private String softScope;

		private String softScopeDesc;

		private Boolean readOnly;

		private String suffix;

		/**
		 * 文字长度限制 [1,2] [,5]
		 */
		private String textLimit;

		/**
		 * 多选答案数量限制 [1, 2] [,3]
		 */
		private String answerLimit;

		private Boolean finish;

		private Integer currentPage;

		private Integer totalPage;

		private String submitButton;

		private Integer numericScale;

		/**
		 * 背景图
		 */
		private String backgroundImage;

		/**
		 * 问卷头背景图
		 */
		private String headerImage;

		/**
		 * 上传文件类型后缀，多个文件格式逗号分割
		 */
		private String fileAccept;

		/**
		 * 最大上传文件数量
		 */
		private Integer maxFileCount;

		/**
		 * 单个上传文件大小限制
		 */
		private Double maxFileSize;

		/**
		 * 打分题显示样式
		 */
		private String scoreStyle;

		/**
		 * Textarea 高度自适应，[4,6] 最低4行，最高6行
		 */
		private String autoSize;

		/**
		 * 只允许使用拍照上传
		 */
		private Boolean cameraOnly;

		/**
		 * 选项排成几列
		 */
		private Integer columns;

		/**
		 * 填空题的题干
		 */
		private String content;

		/**
		 * 是否允许移动地图修改位置
		 */
		private Boolean mapMove;

		/**
		 * 显示投票结果
		 */
		private Boolean statEnabled;

		/**
		 * nps 起始文案
		 */
		private String npsStart;

		/**
		 * nps 结束文案
		 */
		private String npsEnd;

		/**
		 * nps 起始数值
		 */
		private Integer npsStartNum;

		/**
		 * nps 总计数值
		 */
		private Integer npsTotalNum;

		/**
		 * 排序方式，默认正序
		 */
		private Boolean npsInvertSort;

		/**
		 * 结束规则
		 */
		private String finishRule;

		/**
		 * 显示隐藏规则
		 */
		private String visibleRule;

		/**
		 * 必填校验规则
		 */
		private String requiredRule;

		/**
		 * 文本替换规则
		 */
		private String replaceTextRule;

		/**
		 * 校验规则
		 */
		private String validateRule;

		/**
		 * 计算规则
		 */
		private String calculate;

		/**
		 * 多选题选项排他
		 */
		private RejectOtherOption rejectOtherOption;

		/**
		 * 分值
		 */
		private Double examScore;

		/**
		 * 计分方式
		 */
		private ExamScoreMode examAnswerMode;

		/**
		 * 答案匹配规则
		 */
		private ExamMatchRule examMatchRule;

		/**
		 * 考试正确答案，当前选项 id 或者文本
		 */
		private String examCorrectAnswer;

		/**
		 * 答案解析
		 */
		private String examAnalysis;

	}

	public enum ExamMatchRule {

		/** 与答案完全相同才得分 */
		completeSame,
		/** 包含答案，多个答案分号间隔 */
		contain

	}

	public enum RejectOtherOption {

		/**
		 * 选项排他
		 */
		rejectAll,
		/**
		 * 排他选项互斥
		 */
		rejectOther

	}

	public enum ExamScoreMode {

		/** 只有一个选项 */
		onlyOne,
		/** 精确匹配，全部选对得分 */
		selectAll,
		/** 答对几项得几分，打错不得分(单选、多选、下拉) 答对几项得几分(填空) */
		selectCorrect,
		/** 部分选中，按选中分值算分/按选中计分 */
		select,
		/** 人工打分 */
		manual,
		/** 非考试题型 */
		none

	}

	@Data
	public static class DataSource {

		private String label;

		private String value;

		private List<DataSource> children;

		public DataSource() {
		}

		public DataSource(String label, String value, List<DataSource> children) {
			this.label = label;
			this.value = value;
			this.children = children;
		}

	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return new SurveySchema();
		}
	}

}
