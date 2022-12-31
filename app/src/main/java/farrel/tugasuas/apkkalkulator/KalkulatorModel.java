package farrel.tugasuas.apkkalkulator;



public class KalkulatorModel {
    Double angka1, angka2, hasil;
    String operasi;


    public KalkulatorModel(Double angka1, Double angka2, Double hasil, String operasi) {
        this.angka1 = angka1;
        this.angka2 = angka2;
        this.hasil = hasil;
        this.operasi = operasi;
    }

    public Double getAngka1() {
        return angka1;
    }

    public void setAngka1(Double angka1) {
        this.angka1 = angka1;
    }

    public Double getAngka2() {
        return angka2;
    }

    public void setAngka2(Double angka2) {
        this.angka2 = angka2;
    }

    public Double getHasil() {
        return hasil;
    }

    public void setHasil(Double hasil) {
        this.hasil = hasil;
    }

    public String getOperasi() {
        return operasi;
    }

    public void setOperasi(String operasi) {
        this.operasi = operasi;
    }

    @Override
    public String toString() {
        return angka1 + " " + operasi + " " + angka2 + " " + hasil;
    }
}
