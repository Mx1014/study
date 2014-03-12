package graphic.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

//�����ͨ��

public class GetConnecteddomain {

	private int[][] initail; // ԭʼ��ֵͼ
	private Hashtable<Integer, ConnectedDomain> connecteddomaintable = new Hashtable<Integer, ConnectedDomain>(); // �����ͨ��
	private int connecteddomainindex = 0;// ��ͨ���ʶ
	public class Line {
		int linenum; // ����
		int start; // ��ʼ��
		int end; // ������

		public int getLinenum() {

			return linenum;

		}

		public int getStart() {

			return start;

		}

		public int getEnd() {

			return end;

		}

		ArrayList<Integer> belongto = new ArrayList<Integer>();// ��������ͨ�� ��ʱ��
	}

	public void setInitail(int[][] initail) {

		this.initail = initail;

	}

	public Hashtable<Integer, ConnectedDomain> getConnecteddomain() {

		int width = initail.length;
		int height = initail[0].length;
		for (int i = 0; i < width; i++) {//					
			// ��ʼɨ���߶�
			boolean inaline = false;
			Line newline = null;
			for (int j = 0; j < height; j++) {
				if (initail[i][j] == 1) {
					if (!inaline && j != height - 1) {// ǰһ���㲻��1 �������1
						// ��������㲻�����һ��
						// ˵����ʼ��һ�����߶�
						newline = new Line();
						newline.linenum = i;
						newline.start = j;
						inaline = true;
					}
					if (!inaline && j == height - 1) {// ǰһ���㲻��1 �������1
						// ͬʱ����������еĽ���
						// ����������һ��
						newline = new Line();
						newline.linenum = i;
						newline.start = j;
						newline.end = j;
						this.addlinetoconnecteddomain(newline);
					
					}
					if (inaline && j == height - 1) {

						newline.end = j;
						this.addlinetoconnecteddomain(newline);
					}

				}
				if (initail[i][j] == 0) {

					if (inaline) {// ǰһ������1 ����㲻��1 ˵�����߶εĽ���

						newline.end = j - 1;
						addlinetoconnecteddomain(newline);
						inaline = false;
					}

				}

			}
			// ɨ����һ���߶�

		}
		// ������ͨ����
		return this.connecteddomaintable;

	}

	// ���߶η�����ͨ����
	public void addlinetoconnecteddomain(Line newline) {

		Iterator<Integer> itr = this.connecteddomaintable.keySet().iterator();

		while (itr.hasNext()) {
			Integer index = itr.next();
			ConnectedDomain connecteddomain = connecteddomaintable.get(index);
			if (connecteddomain.belongtodomain(newline))
				newline.belongto.add(index);

		}

		// ������߶β������κ���ͨ���� ������һ����ͨͨ��

		if (newline.belongto.size() == 0) {

			ConnectedDomain connecteddomain = new ConnectedDomain();
			connecteddomain.addNewline(newline);

			this.connecteddomaintable.put(new Integer(connecteddomainindex++),
					connecteddomain);
		}

		// �ϲ���ͨ����

		else {

			ConnectedDomain connecteddomain = null;
			for (int k = 0; k < newline.belongto.size(); k++) {
				if (connecteddomain == null) {
					connecteddomain = this.connecteddomaintable
							.get(newline.belongto.get(k));
				} else {
					ConnectedDomain connecteddomaink = this.connecteddomaintable
							.get(newline.belongto.get(k));
					connecteddomain.addConnectedDomain(connecteddomaink);
					this.connecteddomaintable.remove(newline.belongto.get(k));// ɾ�������ϲ�����ͨ��

				}

			}
			connecteddomain.addNewline(newline);

		}

	}

}
