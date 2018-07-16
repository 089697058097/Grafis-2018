package com.project;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author Alfonsius Lorensius
 */
public class Part {

    static GLU glu = new GLU();

    static void pipe(GL gl, float diameter, float height) {
        float radius = diameter / 2;
        GLUquadric q = glu.gluNewQuadric();
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, height / 2, 0.0f);
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, radius, radius, height, 36, 36);
        gl.glPopMatrix();
        glu.gluDeleteQuadric(q);
    }

    static void cylinder(GL gl, float diameter, float height) {
        glu = new GLU();
        float radius = diameter / 2;
        GLUquadric q = glu.gluNewQuadric();
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, height / 2, 0.0f);
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, radius, radius, height, 36, 36);
        glu.gluDisk(q, 0, radius, 36, 36);
        gl.glTranslatef(0.0f, 0.0f, height);
        glu.gluDisk(q, 0, radius, 36, 36);
        gl.glPopMatrix();
        glu.gluDeleteQuadric(q);
    }

    static void cube(GL gl, float diameter, float height) {
        glu = new GLU();
        float radius = diameter / 2;
        GLUquadric q = glu.gluNewQuadric();
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, height / 2, 0.0f);
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        glu.gluCylinder(q, radius, radius, height, 4, 4);
        glu.gluDisk(q, 0, radius, 4, 4);
        gl.glTranslatef(0.0f, 0.0f, height);
        glu.gluDisk(q, 0, radius, 4, 4);
        gl.glPopMatrix();
        glu.gluDeleteQuadric(q);
    }

    static void disk(GL gl, float inner, float outer) {
        glu = new GLU();
        GLUquadric q = glu.gluNewQuadric();
        gl.glPushMatrix();
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        glu.gluDisk(q, inner / 2, outer / 2, 36, 36);
        gl.glPopMatrix();
        glu.gluDeleteQuadric(q);
    }

    static void pistonHead(GL gl) {
        gl.glPushMatrix();
        gl.glColor3f(0.85f, 0.85f, 0.85f);
        pipe(gl, 4.0f, 3.0f);

        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 1.5f, 0.0f);
        gl.glColor3f(0.8f, 0.8f, 0.8f);
        disk(gl, 0.0f, 4.0f);
        gl.glTranslatef(0.0f, -3.0f, 0.0f);
        disk(gl, 3.5f, 4.0f);
        gl.glTranslatef(0.0f, 2.0f, 0.0f);
        disk(gl, 0.0f, 3.5f);
        gl.glTranslatef(0.0f, 0.3f, 0.0f);
        cylinder(gl, 4.01f, 0.2f);
        gl.glTranslatef(0.0f, 0.3f, 0.0f);
        cylinder(gl, 4.01f, 0.2f);
        gl.glPopMatrix();

        gl.glTranslatef(0.0f, -0.5f, 0.0f);
        gl.glColor3f(0.75f, 0.75f, 0.75f);
        pipe(gl, 3.5f, 2.0f);

        gl.glColor3f(0.65f, 0.65f, 0.65f);
        gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
        cylinder(gl, 1, 3.5f);
        gl.glPopMatrix();
    }

    static void pistonStem(GL gl) {
        GLUquadric q = glu.gluNewQuadric();
        double[] eqn = {0.75, 0.0, 1.0, 0.0};

        gl.glPushMatrix();
        gl.glClipPlane(GL.GL_CLIP_PLANE0, eqn, 1);
        gl.glEnable(GL.GL_CLIP_PLANE0);

        gl.glPushMatrix();
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gl.glColor3f(0.85f, 0.85f, 0.85f);
        pipe(gl, 4.0f, 1.5f);
        pipe(gl, 2.5f, 1.5f);
        gl.glColor3f(0.8f, 0.8f, 0.8f);
        gl.glTranslatef(0.0f, 0.75f, 0.0f);
        disk(gl, 2.5f, 4.0f);
        gl.glTranslatef(0.0f, -1.5f, 0.0f);
        disk(gl, 2.5f, 4.0f);
        gl.glPopMatrix();
        gl.glDisable(GL.GL_CLIP_PLANE0);

        gl.glPushMatrix();
        gl.glTranslatef(1.7f, 0.75f, 0.0f);
        feetCube(gl, 1.0f, 1.5f, 1.5f);
        gl.glTranslatef(-3.4f, 0.0f, 0.0f);
        feetCube(gl, 1.0f, 1.5f, 1.5f);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 5.0f, 0.0f);
        feetCube(gl, 1.5f, 7.0f, 1.5f);
        gl.glPopMatrix();
        gl.glPopMatrix();
    }

    static void feetCube(GL gl, float rx, float ry, float rz) {
        float x = rx / 2;
        float y = ry / 2;
        float z = rz / 2;
        gl.glPushMatrix();
        gl.glColor3f(0.85f, 0.85f, 0.85f);

        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3f(-x, y, -z);
        gl.glVertex3f(x, y, -z);
        gl.glVertex3f(x, y, z);
        gl.glVertex3f(-x, y, z);
        gl.glVertex3f(-x, -y, z);
        gl.glVertex3f(-x, -y, -z);
        gl.glEnd();

        gl.glPushMatrix();
        gl.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3f(-x, y, -z);
        gl.glVertex3f(x, y, -z);
        gl.glVertex3f(x, y, z);
        gl.glVertex3f(-x, y, z);
        gl.glVertex3f(-x, -y, z);
        gl.glVertex3f(-x, -y, -z);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glColor3f(0.8f, 0.8f, 0.8f);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-x, y, -z);
        gl.glVertex3f(x, y, -z);
        gl.glVertex3f(x, -y, -z);
        gl.glVertex3f(-x, -y, -z);
        gl.glEnd();
        gl.glTranslatef(0.0f, 0.0f, rz);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-x, y, -z);
        gl.glVertex3f(x, y, -z);
        gl.glVertex3f(x, -y, -z);
        gl.glVertex3f(-x, -y, -z);
        gl.glEnd();
        gl.glPopMatrix();
    }
    
    static void sirip(GL gl) {
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0f, 0.8f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.3f, 0.8f, -1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0.15f, 0f, -1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, 1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0f, 0.8f, 1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.3f, 0.8f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0.15f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0f, 0.8f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0f, 0.8f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0.15f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0.15f, 0.8f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.15f, 0.8f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0.15f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0.15f, 0f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.15f, 0f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0.7f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0.3f, 0.7f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.3f, 0.7f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0f, 0.7f, 1f);
        gl.glEnd();
    }

    static void sirip_banyak(GL gl) {
        sirip(gl);
        gl.glTranslated(0.45, 0, 0);
        sirip(gl);
        gl.glTranslated(0.45, 0, 0);
        sirip(gl);
        gl.glTranslated(0.45, 0, 0);
        sirip(gl);
        gl.glTranslated(0.45, 0, 0);
        sirip(gl);
        gl.glTranslated(0.45, 0, 0);
        sirip(gl);
        gl.glTranslated(0.45, 0, 0);
        sirip(gl);
        gl.glTranslated(0.45, 0, 0);
        sirip(gl);

    }

    static void balok(GL gl) {
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0f, 2.5f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.5f, 2.5f, -1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0.5f, 0f, -1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, 1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0f, 2.5f, 1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.5f, 2.5f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0.5f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0f, 2.5f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0f, 2.5f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0.5f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0.5f, 2.5f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.5f, 2.5f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0.5f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0.5f, 0f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.5f, 0f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 2.5f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0.5f, 2.5f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.5f, 2.5f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0f, 2.5f, 1f);
        gl.glEnd();
    }

    static void balok_tambahan_atas(GL gl) {
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0f, 1.3f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.5f, 1.3f, -1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0.5f, 0f, -1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, 1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0f, 1.3f, 1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.5f, 1.3f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0.5f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0f, 1.3f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0f, 1.3f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0.5f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0.5f, 1.3f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.5f, 1.3f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0.5f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 0f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0.5f, 0f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.5f, 0f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0f, 0f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3f(0f, 1.3f, -1f);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3f(0.5f, 1.3f, -1f);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3f(0.5f, 1.3f, 1f);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3f(0f, 1.3f, 1f);
        gl.glEnd();
    }

    static void body_bandul(GL gl) {
        gl.glTranslated(3, -7, 0);
        balok(gl);
        gl.glPushMatrix();
        gl.glTranslated(0.5, 0, 0);
        gl.glRotated(145, 0, 0, 1);
        balok(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslated(-1, -2.1, 0);
        gl.glRotated(90, 0, 0, 1);
        balok(gl);
        gl.glPopMatrix();
        gl.glTranslated(0.1, 2.2, 0);
        gl.glRotated(55, 0, 0, 1);
        balok(gl);
        gl.glTranslated(0, 2.55, 0);
        gl.glRotated(-55, 0, 0, 1);
        balok(gl);
        gl.glPushMatrix();
        gl.glTranslated(0, 2.5, 0);
        balok_tambahan_atas(gl);

        gl.glPopMatrix();
        gl.glTranslated(1.3, 0, 0);
        gl.glRotated(90, 0, 0, 1);
        sirip_banyak(gl);

    }

    static void sambungan_bandul(GL gl) {
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glVertex3d(1, 1, 0);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3d(-1, 1, 0);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3d(-1.5, -1, 0);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3d(1.5, -1, 0);
        gl.glEnd();
    }

    static void sisi_samping_sambungan_bandul(GL gl) {
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        gl.glColor3d(1, 0, 0);
        gl.glVertex3d(0, 1, 0.5);
        gl.glTexCoord2d(1, 0);
        gl.glVertex3d(0, 1, 0);
        gl.glTexCoord2d(1, 1);
        gl.glVertex3d(0, -1, 0);
        gl.glTexCoord2d(0, 1);
        gl.glVertex3d(0, -1, 0.5);
        gl.glEnd();
    }

    static void bandul(GL gl) {

        gl.glTranslated(0, 1, 0);
        Tabung(gl);
        gl.glPushMatrix();
        gl.glRotated(15, 0, 0, 1);
        gl.glTranslated(1.2, 0, -0.5);
        sisi_samping_sambungan_bandul(gl);
        gl.glRotated(-30, 0, 0, 1);
        gl.glTranslated(-2.2, -0.5, 0);
        sisi_samping_sambungan_bandul(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        sambungan_bandul(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslated(0, 0, -0.5);
        sambungan_bandul(gl);
        gl.glPopMatrix();
        gl.glTranslated(0, -1, -0.5);
        gl.glRotated(180, 0, 0, 1);
        gl.glRotated(-180, 0, 0, 1);
        gl.glTranslated(0, 1, 0);
    }

    static void ring(GL gl) {
        float BODY_LENGTH = 2.5f;
        float BODY_RADIUS = 0.4f;
        int SLICES = 30;
        int STACKS = 30;
        GLU glu = new GLU();
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricTexture(q, true);
        glu.gluCylinder(q, BODY_RADIUS, BODY_RADIUS, BODY_LENGTH, SLICES, STACKS);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);        
        gl.glTranslatef(0.0f, 0.0f, BODY_LENGTH);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);     
    }

    static void Tabung(GL gl) {
        float BODY_LENGTH = 0.5f;
        float BODY_RADIUS = 1.0f;
        int SLICES = 30;
        int STACKS = 30;
        GLU glu = new GLU();
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricTexture(q, true);
        glu.gluCylinder(q, BODY_RADIUS, BODY_RADIUS, BODY_LENGTH, SLICES, STACKS);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);        
        gl.glTranslatef(0.0f, 0.0f, BODY_LENGTH);
        glu.gluDisk(q, 0.0f, BODY_RADIUS, SLICES, STACKS);       

    }
    static void kotak7 (GL gl){ // Tugas
        gl.glPushMatrix();
        gl.glColor3f(0.85f, 0.85f, 0.85f);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-0.5f,-1.5f,1);
        gl.glVertex3f(0.5f,-1.5f,1);
        gl.glVertex3f(0.5f,1.5f,1);
        gl.glVertex3f(-0.5f,1.5f,1);
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-0.5f,-1.5f,-1);
        gl.glVertex3f(0.5f,-1.5f,-1);
        gl.glVertex3f(0.5f,1.5f,-1);
        gl.glVertex3f(-0.5f,1.5f,-1);
        gl.glEnd();
        
        
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(0.5f,-1.5f,-1);
        gl.glVertex3f(0.5f,-1.5f,1);
        gl.glVertex3f(0.5f,1.5f,1);
        gl.glVertex3f(0.5f,1.5f,-1);
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-0.5f,-1.5f,-1);
        gl.glVertex3f(-0.5f,-1.5f,1);
        gl.glVertex3f(-0.5f,1.5f,1);
        gl.glVertex3f(-0.5f,1.5f,-1);
        gl.glEnd();



    }

}
