package annotation;

@MyAnno(value="테스트 클래스")
public class AnnotationTest {

	@MyAnno(value="더하기 수행")
	public void plus(int a, int b) {
		System.out.println(a+" + "+b+" = "+(a+b));
	}
}
