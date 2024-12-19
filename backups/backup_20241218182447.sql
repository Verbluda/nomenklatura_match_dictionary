PGDMP  /                    |            dictionary_db    16.4 (Debian 16.4-1.pgdg120+1)    16.4 (Debian 16.4-1.pgdg120+1)                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                        0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            !           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            "           1262    16384    dictionary_db    DATABASE     x   CREATE DATABASE dictionary_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE dictionary_db;
                admin    false            �            1259    24650    flyway_schema_history    TABLE     �  CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);
 )   DROP TABLE public.flyway_schema_history;
       public         heap    admin    false            �            1259    24659    match    TABLE        CREATE TABLE public.match (
    id bigint NOT NULL,
    row_name character varying,
    nomenklatura_name character varying
);
    DROP TABLE public.match;
       public         heap    admin    false                      0    24650    flyway_schema_history 
   TABLE DATA           �   COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
    public          admin    false    215   �                 0    24659    match 
   TABLE DATA           @   COPY public.match (id, row_name, nomenklatura_name) FROM stdin;
    public          admin    false    216   l       �           2606    24657 .   flyway_schema_history flyway_schema_history_pk 
   CONSTRAINT     x   ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);
 X   ALTER TABLE ONLY public.flyway_schema_history DROP CONSTRAINT flyway_schema_history_pk;
       public            admin    false    215            �           2606    24665    match match_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.match
    ADD CONSTRAINT match_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.match DROP CONSTRAINT match_pkey;
       public            admin    false    216            �           1259    24658    flyway_schema_history_s_idx    INDEX     `   CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);
 /   DROP INDEX public.flyway_schema_history_s_idx;
       public            admin    false    215               �   x�}�K
�0F���*�@B�ko\C'���T�B5��'��3:�גki�\�'ݮ��>����"�;�A@�1O�e���@z�{������*&�i��Z�4�����	�?�cт~��(�>��0�           x�3估�¾�/lP���®�.l���¾�
`�]/6]�p��He\F*\XH��ƜA�ŉ
�ɥ�)�E
A����%�E�j7�ԮL�~S�
'^�}��9@j˅mv\�E�j.3��/l�R�n��w��D��}�3�I4����@��b��D(�9(� ����Db4��/`�(\�qa/Pq�;.�%M5��!�p@հ�t�Вaj��g^IifIf~�fs��%�r3B^JbQ
��eh�]<F��� c�a�     