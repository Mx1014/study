import java.io.StringReader;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;


public class Test0 {
	public static void main(String[] args) {
		StandardAnalyzer sa = new StandardAnalyzer(Version.LUCENE_36);
		sa.tokenStream("", new StringReader("123"));
	}
}
