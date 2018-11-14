/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 * Defines the material type.
 * @author arieh_nigri
 */
public class Material {
    
    private double Kd;   //   Diffusion attenuation coefficient
    private double Ks;   //   Specular attenuation coefficient
    private double Kr;   //   Reflection coefficient (1 for mirror)
    private double Kt;   //   Refraction coefficient (1 for transparent)
    private double n;    //   Refraction index

    public Material(){
        this.Kd = 1.0;
        this.Ks = 1.0;
        this.Kr = 0.0;
        this.Kt = 0.0;
        this.n = 1;
    }
    
    public Material(double Kd, double Ks, double Kr, double Kt, double n) {
        this.setKd(Kd);
        this.setKs(Ks);
        this.setKr(Kr);
        this.setKt(Kt);
        this.setN(n);
    }
    
    public Material(Material obj){
        this.Kd = obj.getKd();
        this.Ks = obj.getKs();
        this.Kr = obj.getKr();
        this.Kt = obj.getKt();
        this.n = obj.getN();
    }
    

    public double getKd() {
        return Kd;
    }

    public final void setKd(double Kd) {
        if(Kd > 1.0)
            this.Kd = 1.0;
        else if(Kd < 0.0)
            this.Kd = 0.0;
        else
            this.Kd = Kd;
    }

    public double getKs() {
        return Ks;
    }

    public final void setKs(double Ks) {
        if(Ks > 1.0)
            this.Ks = 1.0;
        else if(Ks < 0.0)
            this.Ks = 0.0;
        else
            this.Ks = Ks;
    }

    public double getKr() {
        return Kr;
    }

    public final void setKr(double Kr) {
        if(Kr > 1.0)
            this.Kr = 1.0;
        else if(Kr < 0.0)
            this.Kr = 0.0;
        else
            this.Kr = Kr;
    }

    public double getKt() {
        return Kt;
    }

    public final void setKt(double Kt) {
        if(Kt > 1.0)
            this.Kt = 1.0;
        else if(Kt < 0.0)
            this.Kt = 0.0;
        else
            this.Kt = Kt;
    }

    public double getN() {
        return n;
    }

    public final void setN(double n) {
        if(n <= 0.0)
            this.n = 0.0;
        else
            this.n = n;
    }
    
}
